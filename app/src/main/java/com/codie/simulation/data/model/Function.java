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


@Entity(nameInDb = "function")
public class Function {
    @Id(autoincrement = true)
    private Long functionId;

    private long actionId;

    @ToOne(joinProperty = "actionId")
    private Action action;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "description")
    private String description;

    @Property(nameInDb = "return_type")
    private String returnType;

    @ToMany(referencedJoinProperty = "parameterId")
    private List<Parameter> parameters;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 2122828641)
    private transient FunctionDao myDao;

    @Generated(hash = 1275585942)
    public Function(Long functionId, long actionId, String name, String description,
            String returnType) {
        this.functionId = functionId;
        this.actionId = actionId;
        this.name = name;
        this.description = description;
        this.returnType = returnType;
    }

    @Generated(hash = 133141990)
    public Function() {
    }

    public Long getFunctionId() {
        return this.functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public long getActionId() {
        return this.actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
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

    public String getReturnType() {
        return this.returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 61705887)
    public List<Parameter> getParameters() {
        if (parameters == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ParameterDao targetDao = daoSession.getParameterDao();
            List<Parameter> parametersNew = targetDao
                    ._queryFunction_Parameters(functionId);
            synchronized (this) {
                if (parameters == null) {
                    parameters = parametersNew;
                }
            }
        }
        return parameters;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 472917587)
    public synchronized void resetParameters() {
        parameters = null;
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
    @Generated(hash = 1476231130)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFunctionDao() : null;
    }
}
