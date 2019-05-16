
package demo.webmyne.com.retrofitexample.ui;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import demo.webmyne.com.retrofitexample.R;

public class LoadMoreActivity extends AppCompatActivity {
    private Context context;
    private RecyclerView rvReadMore;
    private TextView txtAlert;
    private List<Article> articleList;
    private CategoryArticleAdapter adapter;
    private ArticleRequest articleRequest;
    private int categoryId;
    private String catTitle;
    private LinearLayoutManager linearLayoutManager;
    private int pageCount = 1, firstVisibleItem, visibleItemCount, totalItemCount, previousTotal = 0;
    public boolean loading = true;
    private int mainCount;
    private Toolbar toolbar;
    private TextView txtTitle;
    private boolean catIsMedia;
    private RelativeLayout rlHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);

        getIntentData();
        initToolbar();
        init();
        checkInternetConnection();
        scrollListener();
        actionListener();
    }

    private void getIntentData() {
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            categoryId = bundle.getInt(IntentConstants.CATEGORY_ID);
            catTitle = bundle.getString(IntentConstants.CATEGORY_TITLE);
            catIsMedia = bundle.getBoolean(IntentConstants.CATEGORY_IS_MEDIA, false);
        }
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        toolbar.setTitle("");
        txtTitle.setText(catTitle);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init() {
        context = this;
        rvReadMore = (RecyclerView) findViewById(R.id.rvReadMore);
        txtAlert = (TextView) findViewById(R.id.txtAlert);

        articleList = new ArrayList<>();
    }

    private void checkInternetConnection() {
        Context appContext = CategoryArticleActivity.this;
        if(Utility.isNetworkAvailable(appContext))
        {
            callApi();
        }
        else
        {
            checkVisibility(articleList);

            PreferenceUtility prefUtil = new PreferenceUtility(appContext);
            int bgColor = prefUtil.getThemeSnackBarColor();
            int btnColor = prefUtil.getThemeSnackBarButtonColor();
            int msgColor = prefUtil.getThemeSnackBarMessageColor();

            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), getResources().getString(R.string.NoInternetConnection), Snackbar.LENGTH_INDEFINITE)
                    .setActionTextColor(btnColor)
                    .setAction(getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkInternetConnection();
                        }
                    });
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(bgColor);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(msgColor);

            snackbar.show();
        }
    }

    private void callApi() {
        articleRequest = new ArticleRequest();
        articleRequest.setCategryId(String.valueOf(categoryId));
        articleRequest.setPIndex(String.valueOf(pageCount));
        articleRequest.setPSize(ApiConstant.PAGE_SIZE_CATEGORY_ARTICLE_LIST);
        articleRequest.setPubId(ApiConstant.PUBLISHER_ID);

        new GetArticle(context, catTitle, new GetArticle.OnGetArticle() {
            @Override
            public void onSuccess(ArticleResponse data) {
                closeProgress();
                if (data != null && data.getArticles().size() > 0) {
                    /*rvReadMore.setVisibility(View.VISIBLE);
                    articleList = data.getTable();
                    adapter.setDataList(data.getTable());*/

                    setResponseData(data);

                    mainCount = data.getRelatedArticles().get(0).getPgCount();
                }
                checkVisibility(articleList);
            }

            @Override
            public void onFail() {
                checkVisibility(articleList);
                Functions.showToast(context, getResources().getString(R.string.err_something_went_wrong));
            }

            @Override
            public void onAdsLoaded() {
                if(adapter != null)
                    adapter.setAdsManager();
            }
        }, articleRequest, hasAds);
    }

    private void setResponseData(ArticleResponse data) {
        if (pageCount == 1) {
            if (articleList != null) {
                articleList.clear();
            }
        }
        articleList.addAll(data.getArticles());
        checkVisibility(articleList);
        if (articleList.size() > 0) {
            if (pageCount == 1) {
                initAdapter();
            } else {
                adapter.notifyDataSetChanged();
            }
        }
        pageCount += 1;
    }

    private void initAdapter() {
        rvReadMore.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        rvReadMore.setLayoutManager(linearLayoutManager);

        /*rvReadMore.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(20, 10, 20, 10);
            }
        });*/

        rvReadMore.setItemAnimator(new DefaultItemAnimator());

        adapter = new CategoryArticleAdapter(context, articleList, catTitle, catIsMedia, hasAds);
        rvReadMore.setAdapter(adapter);
    }

    private void actionListener() {

    }

    private void scrollListener() {
        if(hasLoadMore)
        {
            rvReadMore.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    visibleItemCount = rvReadMore.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    firstVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleItemCount)) {
                        if (Functions.checkNetworkConnection(context)) {
                            if (pageCount <= mainCount) {
                                callApi();
                            }

                        } else {
                            Functions.showToast(context, getResources().getString(R.string.err_no_internet_connection));
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    private void checkVisibility(List<Article> articleList) {
        if (articleList != null && articleList.size() > 0) {
            txtAlert.setVisibility(View.GONE);
            rvReadMore.setVisibility(View.VISIBLE);
        } else {
            txtAlert.setVisibility(View.VISIBLE);
            rvReadMore.setVisibility(View.GONE);
        }
    }


}
