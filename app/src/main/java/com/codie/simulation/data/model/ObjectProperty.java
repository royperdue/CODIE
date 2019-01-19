package com.codie.simulation.data.model;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;


@Entity(nameInDb = "object_property")
public class ObjectProperty {
    @Id(autoincrement = true)
    private Long objectPropertyId;

    private long objectId;

    @ToOne(joinProperty = "objectId")
    private DataObject currentDataObject;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "expected_type")
    private String expectedType;

    @Property(nameInDb = "description")
    private String description;

    @Property(nameInDb = "object_height")
    private double objectHeight;

    @Property(nameInDb = "object_width")
    private double objectWidth;

    @Property(nameInDb = "object_depth")
    private double objectDepth;

    @Property(nameInDb = "object_mass")
    private double objectMass;

    @Property(nameInDb = "creation_time")
    private String creationTime;

    @Property(nameInDb = "modification_time")
    private String modificationTime;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1400660224)
    private transient ObjectPropertyDao myDao;

    @Generated(hash = 971890350)
    public ObjectProperty(Long objectPropertyId, long objectId, String name, String expectedType,
            String description, double objectHeight, double objectWidth, double objectDepth,
            double objectMass, String creationTime, String modificationTime) {
        this.objectPropertyId = objectPropertyId;
        this.objectId = objectId;
        this.name = name;
        this.expectedType = expectedType;
        this.description = description;
        this.objectHeight = objectHeight;
        this.objectWidth = objectWidth;
        this.objectDepth = objectDepth;
        this.objectMass = objectMass;
        this.creationTime = creationTime;
        this.modificationTime = modificationTime;
    }

    @Generated(hash = 531787275)
    public ObjectProperty() {
    }

    public Long getObjectPropertyId() {
        return this.objectPropertyId;
    }

    public void setObjectPropertyId(Long objectPropertyId) {
        this.objectPropertyId = objectPropertyId;
    }

    public long getObjectId() {
        return this.objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpectedType() {
        return this.expectedType;
    }

    public void setExpectedType(String expectedType) {
        this.expectedType = expectedType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getObjectHeight() {
        return this.objectHeight;
    }

    public void setObjectHeight(double objectHeight) {
        this.objectHeight = objectHeight;
    }

    public double getObjectWidth() {
        return this.objectWidth;
    }

    public void setObjectWidth(double objectWidth) {
        this.objectWidth = objectWidth;
    }

    public double getObjectDepth() {
        return this.objectDepth;
    }

    public void setObjectDepth(double objectDepth) {
        this.objectDepth = objectDepth;
    }

    public double getObjectMass() {
        return this.objectMass;
    }

    public void setObjectMass(double objectMass) {
        this.objectMass = objectMass;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1783928298)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getObjectPropertyDao() : null;
    }
}
