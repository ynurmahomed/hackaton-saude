package org.nweti;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;
import org.nweti.model.Inquerito;
import org.nweti.perguntas.PerguntasActivity;
import org.nweti.repository.IInqueritoDatasource;
import org.nweti.repository.InqueritoDataSourceMock;
import org.nweti.repository.InqueritoRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mInqueritosRecyclerView;

    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Inquéritos");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mInqueritosRecyclerView = (RecyclerView) findViewById(R.id.inqueritos_recycler_view);
        mInqueritosRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mInqueritosRecyclerView.setLayoutManager(mLayoutManager);
        IInqueritoDatasource repository = new InqueritoRepository(new InqueritoDataSourceMock());
        InqueritosAdapter.InqueritoOnClickListener mOnCLickListener = new InqueritosAdapter.InqueritoOnClickListener() {
            @Override
            public void onClick(Inquerito i) {
                Intent intent = new Intent(MainActivity.this, PerguntasActivity.class);
                intent.putExtra(PerguntasActivity.EXTRA_INQUERITO_ID, i.getId());
                startActivity(intent);

            }
        };
        InqueritosAdapter mAdapter = new InqueritosAdapter(repository.getInqueritos(), mOnCLickListener);
        mInqueritosRecyclerView.setAdapter(mAdapter);

        mProgressView = findViewById(R.id.sync_progress);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        final Handler handler = new Handler();
        if (id == R.id.nav_sicronizar) {
            showProgress(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showProgress(false);
                            Toast.makeText(getApplicationContext(), "Inquéritos sincronizados", Toast.LENGTH_SHORT).show();
                        }
                    }, 1000);
                }
            }).start();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mInqueritosRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
            mInqueritosRecyclerView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mInqueritosRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mInqueritosRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private static class InqueritosAdapter extends RecyclerView.Adapter<InqueritosAdapter.ViewHolder> {

        private List<Inquerito> mDataSet;

        private InqueritoOnClickListener mOnCLickListener;

        public InqueritosAdapter(List<Inquerito> mDataSet, InqueritoOnClickListener mOnCLickListener) {
            this.mDataSet = mDataSet;
            this.mOnCLickListener = mOnCLickListener;
        }

        public interface InqueritoOnClickListener {
            void onClick(Inquerito i);
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public CardView mCardView;
            public TextView mTituloTextView;
            public TextView mInicioTextView;
            public TextView mSessaoTextView;
            public ViewHolder(View v) {
                super(v);
                mCardView = (CardView) v.findViewById(R.id.inquerito_card_view);
                mTituloTextView = (TextView) v.findViewById(R.id.inquerito_titulo);
                mInicioTextView = (TextView) v.findViewById(R.id.inquerito_inicio);
                mSessaoTextView = (TextView) v.findViewById(R.id.inquerito_sessao);
            }
        }

        @Override
        public InqueritosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_inquerito, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(InqueritosAdapter.ViewHolder holder, final int position) {
            final Inquerito i = mDataSet.get(position);
            i.setSessao(2);
            SimpleDateFormat fmt = new SimpleDateFormat("dd 'de' MMMM", new Locale("pt", "BR"));
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnCLickListener.onClick(i);
                }
            });
            holder.mTituloTextView.setText(mDataSet.get(position).getTitulo());
            holder.mInicioTextView.setText(fmt.format(i.getInicio().getTime()));
            holder.mSessaoTextView.setText(i.getSessao() + "ª sessão");
        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }
    }
}
