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


@Entity(nameInDb = "location")
public class Location {
    @Id(autoincrement = true)
    private Long locationId;

    @Property(nameInDb = "objectId")
    private long objectId;

    @ToOne(joinProperty = "objectId")
    private DataObject currentDataObject;

    @Property(nameInDb = "creation_time")
    private String creationTime;

    @Property(nameInDb = "modification_time")
    private String modificationTime;

    @Property(nameInDb = "latitude")
    private String latitude;

    @Property(nameInDb = "longitude")
    private String longitude;

    @Property(nameInDb = "location_type")
    private String locationType;

    @Property(nameInDb = "location_name")
    private String locationName;

    @Property(nameInDb = "description")
    private String description;

    @Property(nameInDb = "phone_number")
    private String phoneNumber;

    @Property(nameInDb = "location_building_number")
    private String locationBuildingNumber;

    @Property(nameInDb = "location_street")
    private String locationStreet;

    @Property(nameInDb = "location_town")
    private String locationTown;

    @Property(nameInDb = "location_state")
    private String locationState;

    @Property(nameInDb = "location_zip_code")
    private String locationZipCode;

    @Property(nameInDb = "location_country")
    private String locationCountry;

    @Property(nameInDb = "rating_value")
    private Long ratingValue;

    @ToMany(referencedJoinProperty = "dateTimeId")
    private List<DateTime> dateTimes;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 842527347)
    private transient LocationDao myDao;

    @Generated(hash = 1665647172)
    public Location(Long locationId, long objectId, String creationTime,
            String modificationTime, String latitude, String longitude,
            String locationType, String locationName, String description,
            String phoneNumber, String locationBuildingNumber,
            String locationStreet, String locationTown, String locationState,
            String locationZipCode, String locationCountry, Long ratingValue) {
        this.locationId = locationId;
        this.objectId = objectId;
        this.creationTime = creationTime;
        this.modificationTime = modificationTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationType = locationType;
        this.locationName = locationName;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.locationBuildingNumber = locationBuildingNumber;
        this.locationStreet = locationStreet;
        this.locationTown = locationTown;
        this.locationState = locationState;
        this.locationZipCode = locationZipCode;
        this.locationCountry = locationCountry;
        this.ratingValue = ratingValue;
    }

    @Generated(hash = 375979639)
    public Location() {
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public long getObjectId() {
        return this.objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
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

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocationType() {
        return this.locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocationBuildingNumber() {
        return this.locationBuildingNumber;
    }

    public void setLocationBuildingNumber(String locationBuildingNumber) {
        this.locationBuildingNumber = locationBuildingNumber;
    }

    public String getLocationStreet() {
        return this.locationStreet;
    }

    public void setLocationStreet(String locationStreet) {
        this.locationStreet = locationStreet;
    }

    public String getLocationTown() {
        return this.locationTown;
    }

    public void setLocationTown(String locationTown) {
        this.locationTown = locationTown;
    }

    public String getLocationState() {
        return this.locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getLocationZipCode() {
        return this.locationZipCode;
    }

    public void setLocationZipCode(String locationZipCode) {
        this.locationZipCode = locationZipCode;
    }

    public String getLocationCountry() {
        return this.locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public Long getRatingValue() {
        return this.ratingValue;
    }

    public void setRatingValue(Long ratingValue) {
        this.ratingValue = ratingValue;
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
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1335898090)
    public List<DateTime> getDateTimes() {
        if (dateTimes == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DateTimeDao targetDao = daoSession.getDateTimeDao();
            List<DateTime> dateTimesNew = targetDao
                    ._queryLocation_DateTimes(locationId);
            synchronized (this) {
                if (dateTimes == null) {
                    dateTimes = dateTimesNew;
                }
            }
        }
        return dateTimes;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1934851424)
    public synchronized void resetDateTimes() {
        dateTimes = null;
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
    @Generated(hash = 1046799944)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLocationDao() : null;
    }
}
