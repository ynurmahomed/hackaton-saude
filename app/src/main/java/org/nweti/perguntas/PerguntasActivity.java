package org.nweti.perguntas;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.nweti.R;
import org.nweti.model.Inquerito;
import org.nweti.model.Pergunta;
import org.nweti.repository.*;

import java.util.List;

public class PerguntasActivity extends AppCompatActivity {

    public static final String EXTRA_INQUERITO_ID = "EXTRA_INQUERITO_ID";

    private RecyclerView mPerguntasRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long inqueritoId = getIntent().getLongExtra(EXTRA_INQUERITO_ID, -1);
        if (inqueritoId == -1) {
            Toast.makeText(getApplicationContext(), "Inquerito não foi informado", Toast.LENGTH_SHORT).show();
            finish();
        }
        IInqueritoDatasource repository = new InqueritoRepository(new InqueritoDataSourceMock());
        Inquerito inquerito = repository.findInquerito(inqueritoId);

        setContentView(R.layout.activity_perguntas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.perguntas_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(inquerito.getTitulo());

        mPerguntasRecyclerView = (RecyclerView) findViewById(R.id.perguntas_recyclerview);
        mPerguntasRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mPerguntasRecyclerView.setLayoutManager(mLayoutManager);
        PerguntasAdapter mAdapter = new PerguntasAdapter(inquerito.getPerguntas());
        mPerguntasRecyclerView.setAdapter(mAdapter);
    }

    private static class PerguntasAdapter extends RecyclerView.Adapter<PerguntasAdapter.ViewHolder> {

        private List<Pergunta> mDataSet;

        public PerguntasAdapter(List<Pergunta> mDataSet) {
            this.mDataSet = mDataSet;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.pergunta_text_view);
            }
        }

        @Override
        public PerguntasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_pergunta, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(PerguntasAdapter.ViewHolder holder, int position) {
            holder.mTextView.setText(mDataSet.get(position).getTexto());
        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }
    }
}
