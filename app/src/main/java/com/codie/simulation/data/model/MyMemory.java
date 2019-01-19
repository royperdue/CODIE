package com.codie.simulation.data.model;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;


@Entity(nameInDb = "myMemory")
public class MyMemory {
    @Id(autoincrement = true)
    private Long myMemoryId;

    @ToMany(referencedJoinProperty = "timeIntervalId")
    private List<TimeInterval> timeIntervals;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 357418999)
    private transient MyMemoryDao myDao;

    @Generated(hash = 973356173)
    public MyMemory(Long myMemoryId) {
        this.myMemoryId = myMemoryId;
    }

    @Generated(hash = 491608924)
    public MyMemory() {
    }

    public Long getMyMemoryId() {
        return this.myMemoryId;
    }

    public void setMyMemoryId(Long myMemoryId) {
        this.myMemoryId = myMemoryId;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 234419847)
    public List<TimeInterval> getTimeIntervals() {
        if (timeIntervals == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TimeIntervalDao targetDao = daoSession.getTimeIntervalDao();
            List<TimeInterval> timeIntervalsNew = targetDao
                    ._queryMyMemory_TimeIntervals(myMemoryId);
            synchronized (this) {
                if (timeIntervals == null) {
                    timeIntervals = timeIntervalsNew;
                }
            }
        }
        return timeIntervals;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2005339011)
    public synchronized void resetTimeIntervals() {
        timeIntervals = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 910665369)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMyMemoryDao() : null;
    }
}
