package com.codie.simulation.data.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.NotNull;


@Entity(nameInDb = "time_interval")
public class TimeInterval {
    @Id(autoincrement = true)
    private Long timeIntervalId;

    private long dateTimeId;

    @ToOne(joinProperty = "dateTimeId")
    private DateTime dateTime;

    @ToMany(referencedJoinProperty = "pointerId")
    private List<Pointer> pointers;

    @ToMany(referencedJoinProperty = "actionId")
    private List<Action> actions;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1609313962)
    private transient TimeIntervalDao myDao;

    @Generated(hash = 675511401)
    private transient Long dateTime__resolvedKey;

    @Generated(hash = 1963500093)
    public TimeInterval(Long timeIntervalId, long dateTimeId) {
        this.timeIntervalId = timeIntervalId;
        this.dateTimeId = dateTimeId;
    }

    @Generated(hash = 1920467264)
    public TimeInterval() {
    }

    public Long getTimeIntervalId() {
        return this.timeIntervalId;
    }

    public void setTimeIntervalId(Long timeIntervalId) {
        this.timeIntervalId = timeIntervalId;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1394889396)
    public List<Pointer> getPointers() {
        if (pointers == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PointerDao targetDao = daoSession.getPointerDao();
            List<Pointer> pointersNew = targetDao
                    ._queryTimeInterval_Pointers(timeIntervalId);
            synchronized (this) {
                if (pointers == null) {
                    pointers = pointersNew;
                }
            }
        }
        return pointers;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 66835006)
    public synchronized void resetPointers() {
        pointers = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 637347285)
    public List<Action> getActions() {
        if (actions == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ActionDao targetDao = daoSession.getActionDao();
            List<Action> actionsNew = targetDao
                    ._queryTimeInterval_Actions(timeIntervalId);
            synchronized (this) {
                if (actions == null) {
                    actions = actionsNew;
                }
            }
        }
        return actions;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1155922067)
    public synchronized void resetActions() {
        actions = null;
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

    public long getDateTimeId() {
        return this.dateTimeId;
    }

    public void setDateTimeId(long dateTimeId) {
        this.dateTimeId = dateTimeId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1190436667)
    public DateTime getDateTime() {
        long __key = this.dateTimeId;
        if (dateTime__resolvedKey == null || !dateTime__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DateTimeDao targetDao = daoSession.getDateTimeDao();
            DateTime dateTimeNew = targetDao.load(__key);
            synchronized (this) {
                dateTime = dateTimeNew;
                dateTime__resolvedKey = __key;
            }
        }
        return dateTime;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 373626639)
    public void setDateTime(@NotNull DateTime dateTime) {
        if (dateTime == null) {
            throw new DaoException(
                    "To-one property 'dateTimeId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.dateTime = dateTime;
            dateTimeId = dateTime.getDateTimeId();
            dateTime__resolvedKey = dateTimeId;
        }
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 755659976)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTimeIntervalDao() : null;
    }
}
