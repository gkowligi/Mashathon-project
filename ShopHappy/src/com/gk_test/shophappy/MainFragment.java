package com.gk_test.shophappy;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.DownloadManager.Request;
import android.os.Bundle;
import android.service.textservice.SpellCheckerService.Session;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment{
	private TextView userInfoTextView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container,
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.activity_main, container, false);

	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    authButton.setReadPermissions(Arrays.asList("user_likes"));

	    return view;

	    
	    userInfoTextView = (TextView) view.findViewById(R.id.userInfoTextView);

	    return view;
	    
	    
	}
	
    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            userInfoTextView.setVisibility(View.VISIBLE);
        } else if (state.isClosed()) {
            userInfoTextView.setVisibility(View.INVISIBLE);
        }
    }
	
	private String buildUserInfoDisplay(GraphUser user) {
	    StringBuilder userInfo = new StringBuilder("");

	    JSONArray likes = (JSONArray)user.getProperty("user-likes");
	    if (likes.length() > 0) {
	        ArrayList<String> like_names = new ArrayList<String> ();
	        for (int i=0; i < likes.length(); i++) {
	            JSONObject like = likes.optJSONObject(i);

	            like_names.add(like.optString("name"));
	        }           
	        userInfo.append(String.format("Languages: %s\n\n", 
	        like_names.toString()));
	    }

	    return userInfo.toString();
	}
	
	if (state.isOpened()) {
	    userInfoTextView.setVisibility(View.VISIBLE);

	    // Request user data and show the results
	    Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

	        @Override
	        public void onCompleted(GraphUser user, Response response) {
	            if (user != null) {
	                // Display the parsed user info
	                userInfoTextView.setText(buildUserInfoDisplay(user));
	            }
	        }
	    });
	}

}


