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


@Entity(nameInDb = "pointer")
public class Pointer {
    @Id(autoincrement = true)
    private Long pointerId;

    private long objectId;

    private long timeIntervalId;

    @ToOne(joinProperty = "objectId")
    private DataObject currentDataObject;

    @ToOne(joinProperty = "timeIntervalId")
    private TimeInterval timeInterval;

    @Property(nameInDb = "associated_object_rating_value")
    private Long associatedObjectRatingValue;

    @Property(nameInDb = "creation_time")
    private String creationTime;

    @Property(nameInDb = "modification_time")
    private String modificationTime;

    @ToMany(referencedJoinProperty = "objectId")
    private List<DataObject> dataObjects;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1725042681)
    private transient PointerDao myDao;

    @Generated(hash = 1640793890)
    public Pointer(Long pointerId, long objectId, long timeIntervalId,
            Long associatedObjectRatingValue, String creationTime,
            String modificationTime) {
        this.pointerId = pointerId;
        this.objectId = objectId;
        this.timeIntervalId = timeIntervalId;
        this.associatedObjectRatingValue = associatedObjectRatingValue;
        this.creationTime = creationTime;
        this.modificationTime = modificationTime;
    }

    @Generated(hash = 1295672034)
    public Pointer() {
    }

    public Long getPointerId() {
        return this.pointerId;
    }

    public void setPointerId(Long pointerId) {
        this.pointerId = pointerId;
    }

    public long getObjectId() {
        return this.objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public long getTimeIntervalId() {
        return this.timeIntervalId;
    }

    public void setTimeIntervalId(long timeIntervalId) {
        this.timeIntervalId = timeIntervalId;
    }

    public Long getAssociatedObjectRatingValue() {
        return this.associatedObjectRatingValue;
    }

    public void setAssociatedObjectRatingValue(Long associatedObjectRatingValue) {
        this.associatedObjectRatingValue = associatedObjectRatingValue;
    }

    public String getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getModificationTime() {
        return this.modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

    @Generated(hash = 1950372229)
    private transient Long currentDataObject__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 929932081)
    public DataObject getCurrentDataObject() {
        long __key = this.objectId;
        if (currentDataObject__resolvedKey == null
                || !currentDataObject__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DataObjectDao targetDao = daoSession.getDataObjectDao();
            DataObject currentDataObjectNew = targetDao.load(__key);
            synchronized (this) {
                currentDataObject = currentDataObjectNew;
                currentDataObject__resolvedKey = __key;
            }
        }
        return currentDataObject;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1681320941)
    public void setCurrentDataObject(@NotNull DataObject currentDataObject) {
        if (currentDataObject == null) {
            throw new DaoException(
                    "To-one property 'objectId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.currentDataObject = currentDataObject;
            objectId = currentDataObject.getObjectId();
            currentDataObject__resolvedKey = objectId;
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
    @Generated(hash = 114575562)
    public List<DataObject> getDataObjects() {
        if (dataObjects == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DataObjectDao targetDao = daoSession.getDataObjectDao();
            List<DataObject> dataObjectsNew = targetDao
                    ._queryPointer_DataObjects(pointerId);
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
    @Generated(hash = 95979166)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPointerDao() : null;
    }
}
