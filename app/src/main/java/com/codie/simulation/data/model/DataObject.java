package com.codie.simulation.data.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;


/**
 * Created by Roy Perdue on 08/12/16.
 */
@Entity(nameInDb = "object")
public class DataObject {

    @Id(autoincrement = true)
    private Long objectId;

    private long actionId;

    @ToOne(joinProperty = "actionId")
    private Action action;

    private long pointerId;

    @ToOne(joinProperty = "pointerId")
    private Pointer pointer;

    @Property(nameInDb = "type")
    private String type;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "number_times_encountered")
    private int numberTimesEncountered;

    @Property(nameInDb = "confidence_value")
    private float confidenceValue;

    @Property(nameInDb = "image_path")
    private String imagePath;

    @Property(nameInDb = "is_component_object")
    private boolean isComponentObject;

    @Property(nameInDb = "created_at")
    private String createdAt;

    @Property(nameInDb = "updated_at")
    private String updatedAt;

    @ToMany(referencedJoinProperty = "pointerId")
    private List<Pointer> pointers;

    @ToMany(referencedJoinProperty = "objectPropertyId")
    private List<ObjectProperty> objectProperties;

    @ToMany(referencedJoinProperty = "locationId")
    private List<Location> locations;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 335826961)
    private transient DataObjectDao myDao;

    @Generated(hash = 1191194268)
    public DataObject(Long objectId, long actionId, long pointerId, String type, String name,
            int numberTimesEncountered, float confidenceValue, String imagePath,
            boolean isComponentObject, String createdAt, String updatedAt) {
        this.objectId = objectId;
        this.actionId = actionId;
        this.pointerId = pointerId;
        this.type = type;
        this.name = name;
        this.numberTimesEncountered = numberTimesEncountered;
        this.confidenceValue = confidenceValue;
        this.imagePath = imagePath;
        this.isComponentObject = isComponentObject;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Generated(hash = 58657257)
    public DataObject() {
    }

    public Long getObjectId() {
        return this.objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public long getActionId() {
        return this.actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
    }

    public long getPointerId() {
        return this.pointerId;
    }

    public void setPointerId(long pointerId) {
        this.pointerId = pointerId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsComponentObject() {
        return this.isComponentObject;
    }

    public void setIsComponentObject(boolean isComponentObject) {
        this.isComponentObject = isComponentObject;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated(hash = 462335395)
    private transient Long action__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2001125006)
    public Action getAction() {
        long __key = this.actionId;
        if (action__resolvedKey == null || !action__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ActionDao targetDao = daoSession.getActionDao();
            Action actionNew = targetDao.load(__key);
            synchronized (this) {
                action = actionNew;
                action__resolvedKey = __key;
            }
        }
        return action;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1736162546)
    public void setAction(@NotNull Action action) {
        if (action == null) {
            throw new DaoException(
                    "To-one property 'actionId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.action = action;
            actionId = action.getActionId();
            action__resolvedKey = actionId;
        }
    }

    @Generated(hash = 2126432413)
    private transient Long pointer__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 255397675)
    public Pointer getPointer() {
        long __key = this.pointerId;
        if (pointer__resolvedKey == null || !pointer__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PointerDao targetDao = daoSession.getPointerDao();
            Pointer pointerNew = targetDao.load(__key);
            synchronized (this) {
                pointer = pointerNew;
                pointer__resolvedKey = __key;
            }
        }
        return pointer;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1635157016)
    public void setPointer(@NotNull Pointer pointer) {
        if (pointer == null) {
            throw new DaoException(
                    "To-one property 'pointerId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.pointer = pointer;
            pointerId = pointer.getPointerId();
            pointer__resolvedKey = pointerId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1403710007)
    public List<Pointer> getPointers() {
        if (pointers == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PointerDao targetDao = daoSession.getPointerDao();
            List<Pointer> pointersNew = targetDao
                    ._queryDataObject_Pointers(objectId);
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
    @Generated(hash = 1322752101)
    public List<ObjectProperty> getObjectProperties() {
        if (objectProperties == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ObjectPropertyDao targetDao = daoSession.getObjectPropertyDao();
            List<ObjectProperty> objectPropertiesNew = targetDao
                    ._queryDataObject_ObjectProperties(objectId);
            synchronized (this) {
                if (objectProperties == null) {
                    objectProperties = objectPropertiesNew;
                }
            }
        }
        return objectProperties;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 692594068)
    public synchronized void resetObjectProperties() {
        objectProperties = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1846873379)
    public List<Location> getLocations() {
        if (locations == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LocationDao targetDao = daoSession.getLocationDao();
            List<Location> locationsNew = targetDao
                    ._queryDataObject_Locations(objectId);
            synchronized (this) {
                if (locations == null) {
                    locations = locationsNew;
                }
            }
        }
        return locations;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1398170159)
    public synchronized void resetLocations() {
        locations = null;
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

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public float getConfidenceValue() {
        return this.confidenceValue;
    }

    public void setConfidenceValue(float confidenceValue) {
        this.confidenceValue = confidenceValue;
    }

    public int getNumberTimesEncountered() {
        return this.numberTimesEncountered;
    }

    public void setNumberTimesEncountered(int numberTimesEncountered) {
        this.numberTimesEncountered = numberTimesEncountered;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 747115845)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDataObjectDao() : null;
    }
}