package com.blogspot.notes.automation.qa.core.core;

import com.blogspot.notes.automation.qa.core.db.dao.DAO;
import com.blogspot.notes.automation.qa.core.enums.Schema;
import com.blogspot.notes.automation.qa.core.model.UsersEntity;
import com.blogspot.notes.automation.qa.core.pool.ClientUsersListener;
import com.blogspot.notes.automation.qa.core.pool.UsersFactory;
import javaslang.control.Try;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.vibur.objectpool.ConcurrentLinkedPool;
import org.vibur.objectpool.PoolService;

import java.util.Optional;

import static com.blogspot.notes.automation.qa.core.db.utils.HibernateUtils.closeConnections;
import static com.blogspot.notes.automation.qa.core.utils.PropertyUtils.Constants.*;

public class BaseTest {

	private static PoolService<UsersEntity> poolService;

	@BeforeSuite
	public void setUp() {
		Try.run(() -> poolService = new ConcurrentLinkedPool<>(getUsersFactory(),
				MIN_POOL_SIZE, MAX_POOL_SIZE, IS_POOL_FAIR, new ClientUsersListener()));
	}

	@AfterSuite
	public void tearDown() {
		getPool().ifPresent(PoolService::terminate);
		closeConnections();
	}

	public static Optional<PoolService<UsersEntity>> getPool() {
		return Optional.ofNullable(poolService);
	}

	private UsersFactory getUsersFactory() {
		return new UsersFactory(new DAO<>(UsersEntity.class, Schema.AUTOMATION).selectAll());
	}
}
