package com.blogspot.notes.automation.qa.core.pool;

import com.blogspot.notes.automation.qa.core.model.UsersEntity;
import org.vibur.objectpool.listener.Listener;

public class ClientUsersListener implements Listener<UsersEntity> {
	@Override
	public void onTake(UsersEntity object) {
		System.out.println("Took " + object);
	}

	@Override
	public void onRestore(UsersEntity object) {
		System.out.println("Released " + object);
	}
}
