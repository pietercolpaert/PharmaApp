package mobi.pharmaapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import mobi.pharmaapp.models.DataModel;
import mobi.pharmaapp.util.JSONPharmacyScraper;

/**
 *
 * @author see /AUTHORS
 */
public class LoadDataDialog extends AsyncTask<Void, Void, Integer> {

    private final Activity parent;
    protected ProgressDialog dialog;

    public LoadDataDialog(Activity parent) {
        this.parent = parent;
        dialog = new ProgressDialog(parent);
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Loading data...");
        dialog.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return JSONPharmacyScraper.loadData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        dialog.dismiss();
        if (result.intValue() == 1) {
            showErrorDialogAndExit();
        }
    }

    protected void showErrorDialogAndExit() {
        AlertDialog.Builder alert = new AlertDialog.Builder(parent);
        alert.setTitle("No internet connection available!");
        alert.setMessage("You need an internet connection to refresh to list of pharmacists.");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
    }
}