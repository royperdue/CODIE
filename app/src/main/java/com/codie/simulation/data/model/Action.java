package com.codie.simulation.data.model;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;


@Entity(nameInDb = "interface")
public class Action {
    @Id(autoincrement = true)
    private Long actionId;

    private long dateTimeId;
    private long timeIntervalId;

    @ToOne(joinProperty = "dateTimeId")
    private DateTime dateTime;

    @ToOne(joinProperty = "timeIntervalId")
    private TimeInterval timeInterval;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "description")
    private String description;

    @Property(nameInDb = "start_time")
    private String startTime;

    @Property(nameInDb = "end_time")
    private String endTime;

    @ToMany(referencedJoinProperty = "functionId")
    private List<Function> functions;

    @ToMany(referencedJoinProperty = "objectId")
    private List<DataObject> dataObjects;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 176138947)
    private transient ActionDao myDao;

    @Generated(hash = 2099000576)
    public Action(Long actionId, long dateTimeId, long timeIntervalId, String name,
            String description, String startTime, String endTime) {
        this.actionId = actionId;
        this.dateTimeId = dateTimeId;
        this.timeIntervalId = timeIntervalId;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Generated(hash = 2056262033)
    public Action() {
    }

    public Long getActionId() {
        return this.actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public long getDateTimeId() {
        return this.dateTimeId;
    }

    public void setDateTimeId(long dateTimeId) {
        this.dateTimeId = dateTimeId;
    }

    public long getTimeIntervalId() {
        return this.timeIntervalId;
    }

    public void setTimeIntervalId(long timeIntervalId) {
        this.timeIntervalId = timeIntervalId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Generated(hash = 675511401)
    private transient Long dateTime__resolvedKey;

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

    @Generated(hash = 1390317163)
    private transient Long timeInterval__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1102988785)
    public TimeInterval getTimeInterval() {
        long __key = this.timeIntervalId;
        if (timeInterval__resolvedKey == null
                || !timeInterval__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TimeIntervalDao targetDao = daoSession.getTimeIntervalDao();
            TimeInterval timeIntervalNew = targetDao.load(__key);
            synchronized (this) {
                timeInterval = timeIntervalNew;
                timeInterval__resolvedKey = __key;
            }
        }
        return timeInterval;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1106417672)
    public void setTimeInterval(@NotNull TimeInterval timeInterval) {
        if (timeInterval == null) {
            throw new DaoException(
                    "To-one property 'timeIntervalId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.timeInterval = timeInterval;
            timeIntervalId = timeInterval.getTimeIntervalId();
            timeInterval__resolvedKey = timeIntervalId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 857675274)
    public List<Function> getFunctions() {
        if (functions == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FunctionDao targetDao = daoSession.getFunctionDao();
            List<Function> functionsNew = targetDao
                    ._queryAction_Functions(actionId);
            synchronized (this) {
                if (functions == null) {
                    functions = functionsNew;
                }
            }
        }
        return functions;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1200505502)
    public synchronized void resetFunctions() {
        functions = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 597200186)
    public List<DataObject> getDataObjects() {
        if (dataObjects == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DataObjectDao targetDao = daoSession.getDataObjectDao();
            List<DataObject> dataObjectsNew = targetDao
                    ._queryAction_DataObjects(actionId);
            synchronized (this) {
                if (dataObjects == null) {
                    dataObjects = dataObjectsNew;
                }
            }
        }
        return dataObjects;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1128475283)
    public synchronized void resetDataObjects() {
        dataObjects = null;
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
    @Generated(hash = 1450921996)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getActionDao() : null;
    }
}
