package com.example.lzw.myapp.Dialogs;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lzw.myapp.R;

/**
 * Created by Administrator on 2017/6/17.
 */

public class DialogActivity extends Activity implements OnDialogDoneListener {

    public static final String LOGTAG = "DialogFragDemo";

    public static final String ALERT_DIALOG_TAG = "ALERT_DIALOG_TAG";

    public static final String HELP_DIALOG_TAG = "HELP_DIALOG_TAG";

    public static final String PROMPT_DIALOG_TAG = "PROMPT_DIALOG_TAG";

    public static final String EMBED_DIALOG_TAG = "EMBEDDED_DIALOG_TAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_dialog_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.dialogs_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_show_alert_dialog:
                this.showAlertDialog();
                break;
            case R.id.menu_show_prompt_dialog:
                this.showPromptDialog();
                break;
            case R.id.menu_help:
                this.showHelpDialog();
                break;
            case R.id.menu_embedded:
                this.showEmbeddedDialog();
                break;
        }

        return true;
    }


    private void showAlertDialog()
    {
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        AlertDialogFragment adf=AlertDialogFragment.newInstance("Alert Message");
        adf.show(ft,ALERT_DIALOG_TAG);
    }


    private void showPromptDialog() {
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        PromptDialogFragment pdf=PromptDialogFragment.newInstance("Enter Something");
        pdf.show(ft,PROMPT_DIALOG_TAG);
    }

    private void showHelpDialog() {
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        HelpDialogFragment hdf=HelpDialogFragment.newInstance(R.string.help_text);
        hdf.show(ft,HELP_DIALOG_TAG);
    }

    private void showEmbeddedDialog()
    {
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        PromptDialogFragment pdf=PromptDialogFragment.newInstance("Enter Something");
        ft.add(pdf,EMBED_DIALOG_TAG);
        ft.commit();
    }

    @Override
    public void onDialogDone(String tag, boolean cancelled, CharSequence message) {
        String msg=tag+" responds with: "+message;
        if(cancelled)
        {
            msg=tag+" was cancelled by the user";
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        Log.v("DialogActivity",msg);
    }
}
