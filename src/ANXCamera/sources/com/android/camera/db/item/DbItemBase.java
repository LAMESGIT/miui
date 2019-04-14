package com.android.camera.db.item;

import com.android.camera.db.DbContainer;
import com.android.camera.db.greendao.DaoMaster;
import com.android.camera.db.greendao.DaoSession;
import com.android.camera.db.provider.DbProvider.providerDb;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.Query;

public abstract class DbItemBase<T, K> implements providerDb<T, K> {
    protected final Object mLock = new Object();

    public abstract T createItem(long j);

    public abstract T endItem(T t, long j);

    public abstract AbstractDao<T, K> getDao();

    public abstract Property getOrderProperty();

    public abstract String provideTableName();

    public long getCount() {
        long count;
        synchronized (this.mLock) {
            count = getDao().queryBuilder().buildCount().count();
        }
        return count;
    }

    public T generateItem(long j) {
        Object createItem;
        synchronized (this.mLock) {
            createItem = createItem(j);
        }
        return createItem;
    }

    public long endItemAndInsert(T t, long j) {
        long insertOrReplace;
        synchronized (this.mLock) {
            insertOrReplace = insertOrReplace(endItem(t, j));
        }
        return insertOrReplace;
    }

    public void updateItem(T t) {
        synchronized (this.mLock) {
            updateItemThroughDb(t);
        }
    }

    public void removeItem(T t) {
        synchronized (this.mLock) {
            getDaoSession().delete(t);
        }
    }

    public List<T> getAllItems() {
        List loadAll;
        synchronized (this.mLock) {
            loadAll = getDao().loadAll();
        }
        return loadAll;
    }

    public T getItemByPath(String str) {
        throw new RuntimeException("todo");
    }

    public T getItemByMediaId(Long l) {
        throw new RuntimeException("todo");
    }

    public T getItemWithExistedQuery(Query query, String str) {
        throw new RuntimeException("todo");
    }

    public T getLastItem() {
        synchronized (this.mLock) {
            List list = getDao().queryBuilder().limit(1).orderDesc(getOrderProperty()).list();
            if (list == null || list.isEmpty()) {
                return null;
            }
            Object obj = list.get(0);
            return obj;
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final long insertOrReplace(T t) {
        return getDaoSession().insertOrReplace(t);
    }

    /* Access modifiers changed, original: protected|final */
    public final void updateItemThroughDb(T t) {
        getDaoSession().update(t);
    }

    /* Access modifiers changed, original: protected|final */
    public final DaoMaster getDaoMaser() {
        return DbContainer.getInstance().getDaoMaster();
    }

    /* Access modifiers changed, original: protected|final */
    public final DaoSession getDaoSession() {
        return DbContainer.getInstance().getDaoSession();
    }
}
