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


@Entity(nameInDb = "date_time")
public class DateTime {
    @Id(autoincrement = true)
    private Long dateTimeId;

    private long locationId;

    @ToOne(joinProperty = "locationId")
    private Location location;

    @Property(nameInDb = "month")
    private String month;

    @Property(nameInDb = "day")
    private String day;

    @Property(nameInDb = "year")
    private String year;

    @Property(nameInDb = "time")
    private String time;

    @Property(nameInDb = "temperature")
    private String temperature;

    @Property(nameInDb = "humidity")
    private String humidity;

    @ToMany(referencedJoinProperty = "actionId")
    private List<Action> actions;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 137889135)
    private transient DateTimeDao myDao;

    @Generated(hash = 400613192)
    public DateTime(Long dateTimeId, long locationId, String month, String day,
            String year, String time, String temperature, String humidity) {
        this.dateTimeId = dateTimeId;
        this.locationId = locationId;
        this.month = month;
        this.day = day;
        this.year = year;
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Generated(hash = 2052035484)
    public DateTime() {
    }

    public Long getDateTimeId() {
        return this.dateTimeId;
    }

    public void setDateTimeId(Long dateTimeId) {
        this.dateTimeId = dateTimeId;
    }

    public long getLocationId() {
        return this.locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return this.humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Generated(hash = 1068795426)
    private transient Long location__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 469564222)
    public Location getLocation() {
        long __key = this.locationId;
        if (location__resolvedKey == null || !location__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LocationDao targetDao = daoSession.getLocationDao();
            Location locationNew = targetDao.load(__key);
            synchronized (this) {
                location = locationNew;
                location__resolvedKey = __key;
            }
        }
        return location;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1500142957)
    public void setLocation(@NotNull Location location) {
        if (location == null) {
            throw new DaoException(
                    "To-one property 'locationId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.location = location;
            locationId = location.getLocationId();
            location__resolvedKey = locationId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 824980450)
    public List<Action> getActions() {
        if (actions == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ActionDao targetDao = daoSession.getActionDao();
            List<Action> actionsNew = targetDao._queryDateTime_Actions(dateTimeId);
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1285184671)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDateTimeDao() : null;
    }
}
