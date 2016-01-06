package com.example.eventbusdemo.classic.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.classic.controller.DefaultThirdViewControllerImpl;
import com.example.eventbusdemo.classic.controller.ThirdViewController;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class ThirdFragment extends Fragment {

    private ThirdViewController viewController;

    private StringBuilder logMessage;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewController == null) {
            viewController = new DefaultThirdViewControllerImpl();
        }

        logMessage = new StringBuilder("Emulate long running Task:");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.third_fragment, container, false);
        viewController.setupView(view);
        viewController.displayLogText(logMessage.toString());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new NetworkAsyncTask().execute();
    }

    private class NetworkAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            logMessage.append("\n Emulated Network process finished");
            viewController.displayLogText(logMessage.toString());

            new NetworkResultProcessAsyncTask().execute();
        }
    }

    private class NetworkResultProcessAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            logMessage.append("\n Emulated Result process finished");
            viewController.displayLogText(logMessage.toString());

            new PersistAsyncTask().execute();
        }
    }

    private class PersistAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            logMessage.append("\n Emulated Persist process finished");
            viewController.displayLogText(logMessage.toString());
        }
    }
}