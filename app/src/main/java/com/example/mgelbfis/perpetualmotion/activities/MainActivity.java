package com.example.mgelbfis.perpetualmotion.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mgelbfis.perpetualmotion.R;
import com.example.mgelbfis.perpetualmotion.id_game.PopIT;
import com.example.mgelbfis.perpetualmotion.lib.CardPilesAdapter;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private PopIT mCurrentGame;
    private CardPilesAdapter mAdapter;
    private TextView mTv_CardsRemaining, mTv_CardsInDeck;
    private View mSbContainer;

    private boolean[] mCheckedPiles;

    //to same and restore state of game need to save whats checked and the game, the game will save itself with serializeable
    private final String mKeyCheckPiles = "CheckedPiles";
    private final String mKeyGame = "Game";

    @Override
    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBooleanArray(mKeyCheckPiles, mCheckedPiles);
        outState.putString(mKeyGame, getJSONof(mCurrentGame));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupGUI();
        setupBoard();
        doInitialStartGame(savedInstanceState);

       
    }

    private void doInitialStartGame(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentGame = restoreGameFromJSON(savedInstanceState.getString(mKeyGame));
            doPostTurnUpdates();
            mAdapter.overwriteChecksFrom(savedInstanceState.getBooleanArray(mKeyCheckPiles));
        } else {
            startGame();
        }
    }

    private void doPostTurnUpdates() {
    }

    private void setupBoard() {
        mCheckedPiles = new boolean[] {false, false, false, false};
        mAdapter = new CardPilesAdapter(mCheckedPiles, getString(R.string.cards_in_stack));
        //TODO: set Listener

        RecyclerView piles = (RecyclerView) findViewById(R.id.rv_piles);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.rv_columns));

        layoutManager.setAutoMeasureEnabled(true);

        piles.setHasFixedSize(true);
        piles.setLayoutManager(layoutManager);
        piles.setAdapter(mAdapter);
    }
    
    

    private void setupGUI() {
        mSbContainer = findViewById(R.id.cl_main);
        mTv_CardsRemaining = (TextView) findViewById(R.id.tv_cards_remaining_to_discard);
        mTv_CardsInDeck = (TextView) findViewById(R.id.tv_cards_in_deck);
    }

    private void startGame() {

    }


    private void setupFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     //   if (id == R.id.action_settings) {
      //      return true;
       // }

        return super.onOptionsItemSelected(item);
    }

    private String getJSONof (PopIT obj)
    {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    private PopIT restoreGameFromJSON (String json){
        Gson gson = new Gson();
        return gson.fromJson(json, PopIT.class);
    }
}
