package com.IDP.Group1.acr;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class CommonCLass {
	public void writeData(User user) {
		String p = FirebaseAuth.getInstance().getCurrentUser().getEmail();

		p = p.replace('.', '_');
		p = p.replace('@', '_');

		DatabaseReference dr = FirebaseDatabase.getInstance().getReference(p);
		dr.setValue(user);
	}

	public static <T> List<T> convertArrayToList(T[] array)
	{
		return Arrays.asList(array);
	}

//	public static <T> T[] convertListToArray(List <T> l) {
//
//	}
}
