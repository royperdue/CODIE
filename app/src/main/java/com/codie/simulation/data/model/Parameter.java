package com.codie.simulation.data.model;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;


@Entity(nameInDb = "parameter")
public class Parameter {
    @Id(autoincrement = true)
    private Long parameterId;

    private long functionId;

    @ToOne(joinProperty = "functionId")
    private Function function;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "data_type")
    private String dataType;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1846945482)
    private transient ParameterDao myDao;

    @Generated(hash = 185033811)
    public Parameter(Long parameterId, long functionId, String name,
            String dataType) {
        this.parameterId = parameterId;
        this.functionId = functionId;
        this.name = name;
        this.dataType = dataType;
    }

    @Generated(hash = 1889793194)
    public Parameter() {
    }

    public Long getParameterId() {
        return this.parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    public long getFunctionId() {
        return this.functionId;
    }

    public void setFunctionId(long functionId) {
        this.functionId = functionId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Generated(hash = 351893671)
    private transient Long function__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 746865417)
    public Function getFunction() {
        long __key = this.functionId;
        if (function__resolvedKey == null || !function__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FunctionDao targetDao = daoSession.getFunctionDao();
            Function functionNew = targetDao.load(__key);
            synchronized (this) {
                function = functionNew;
                function__resolvedKey = __key;
            }
        }
        return function;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1721894967)
    public void setFunction(@NotNull Function function) {
        if (function == null) {
            throw new DaoException(
                    "To-one property 'functionId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.function = function;
            functionId = function.getFunctionId();
            function__resolvedKey = functionId;
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1757326774)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getParameterDao() : null;
    }
}
