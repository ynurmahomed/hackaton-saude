package org.nwety.perguntas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.nwety.R;
import org.nwety.model.Pergunta;
import org.nwety.repository.IPerguntaDatasource;
import org.nwety.repository.PerguntaDatasourceMock;
import org.nwety.repository.PerguntaRepository;

import java.util.List;

public class PerguntasActivity extends AppCompatActivity {

    private RecyclerView mPerguntasRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);

        mPerguntasRecyclerView = (RecyclerView) findViewById(R.id.perguntas_recyclerview);
        mPerguntasRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mPerguntasRecyclerView.setLayoutManager(mLayoutManager);
        IPerguntaDatasource repository = new PerguntaRepository(new PerguntaDatasourceMock());
        PerguntasAdapter mAdapter = new PerguntasAdapter(repository.getPerguntas());
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
