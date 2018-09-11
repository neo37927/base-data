package com.oop.api.jardatamybatissharding.dataSource.session;


import com.oop.api.jardatamybatissharding.exception.DataAccessException;
import com.oop.api.jardatamybatissharding.constant.Constants;
import com.oop.api.jardatamybatissharding.dataSource.param.CustomSQL;
import com.oop.api.jardatamybatissharding.dataSource.param.ESQL;
import com.oop.api.jardatamybatissharding.dataSource.param.Param;
import com.oop.api.jardatamybatissharding.dataSource.param.SQLCreator;
import com.oop.api.jardatamybatissharding.util.DateUtils;
import com.oop.api.jardatamybatissharding.util.SQLUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefaultDataSession implements DataSession {

    public final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private SqlSession session;

    private static final String UPDATE_STRING = "updateSqlStr";
    private static final String WHERE_STRING = "whereSqlStr";

    public DefaultDataSession() {
    }

    public DefaultDataSession(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public SqlSession getSession() {
        if (session == null)
            session = new SqlSessionTemplate(sqlSessionFactory);
        return session;
    }

    @Override
    public <T> T save(Class<T> clazz, T po) throws DataAccessException {
        try {
            SqlSession session = getSession();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("obj", po);
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.SAVE).get(), map));
            session.insert(SQLCreator.set(clazz, ESQL.SAVE).get(), po);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return po;
    }


    @Override
    public <T> int update(Class<T> clazz, T po) throws DataAccessException {
        try {
            SqlSession session = getSession();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("obj", po);
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.UPDATE).get(), map));
            return session.update(SQLCreator.set(clazz, ESQL.UPDATE).get(), po);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }


    @Override
    public <T> int logicDelete(Class<T> clazz, Param param) throws DataAccessException {
        try {
            SqlSession session = getSession();
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.LOGICDELETE).get(), param.get()));
            return session.update(SQLCreator.set(clazz, ESQL.LOGICDELETE).get(), param.get());
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> int physicalDelete(Class<T> clazz, Integer id) throws DataAccessException {
        try {
            SqlSession session = getSession();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.PHYSICALDELETE).get(), map));
            return session.delete(SQLCreator.set(clazz, ESQL.PHYSICALDELETE).get(), id);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> T querySingleResultById(Class<T> clazz, int id) throws DataAccessException {
        try {
            SqlSession session = getSession();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.QUERYSINGLERESULTBYID).get(), map));
            return session.selectOne(SQLCreator.set(clazz, ESQL.QUERYSINGLERESULTBYID).get(), id);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> T querySingleResultByUUID(Class<T> clazz, String uuid) throws DataAccessException {
        try {
            SqlSession session = getSession();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uuid", uuid);
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.QUERYSINGLERESULTBYUUID).get(), map));
            return session.selectOne(SQLCreator.set(clazz, ESQL.QUERYSINGLERESULTBYUUID).get(), uuid);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> T querySingleResultByParams(Class<T> clazz, Param param) throws DataAccessException {
        try {
            SqlSession session = getSession();
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.QUERYSINGLERESULTBYPARAMS).get(), param.get()));
            return session.selectOne(SQLCreator.set(clazz, ESQL.QUERYSINGLERESULTBYPARAMS).get(), param.get());
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> List<T> queryListResult(Class<T> clazz, Param param) throws DataAccessException {
        try {
            SqlSession session = getSession();
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.QUERYLISTRESULT).get(), param.get()));
            return session.selectList(SQLCreator.set(clazz, ESQL.QUERYLISTRESULT).get(), param.get());
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> long queryListResultCount(Class<T> clazz, Param param) throws DataAccessException {
        try {
            SqlSession session = getSession();
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.QUERYLISTRESULTCOUNT).get(), param.get()));
            return session.selectOne(SQLCreator.set(clazz, ESQL.QUERYLISTRESULTCOUNT).get(), param.get());
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }


    @Override
    public <T> T querySingleVO(Class<T> formater, Throwable able, Param param) throws DataAccessException {
        try {
            SqlSession session = getSession();
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(Constants.SQLConstants.CUSTOMER_SQL, able).get(), param.get()));
            long start = System.currentTimeMillis();
            T t = session.selectOne(SQLCreator.set(Constants.SQLConstants.CUSTOMER_SQL, able).get(), param.get());
            logger.info("{}方法SQL执行耗费时间为:{}", able.getStackTrace()[0].getMethodName(), DateUtils.fromMillisToTime(System.currentTimeMillis() - start));
            return t;
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> T querySingleVOByCustomElementName(Class<T> formater, String elementId, Param param)
            throws DataAccessException {
        try {
            SqlSession session = getSession();
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(Constants.SQLConstants.CUSTOMER_SQL, elementId).get(), param.get()));
            return session.selectOne(SQLCreator.set(Constants.SQLConstants.CUSTOMER_SQL, elementId).get(), param.get());
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }


    @Override
    public <T> List<T> queryVOList(Class<T> clazz, Throwable able, Param param) throws DataAccessException {
        try {
            SqlSession session = getSession();
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(Constants.SQLConstants.CUSTOMER_SQL, able).get(), param.get()));
            List<T> result = session.selectList(SQLCreator.set(Constants.SQLConstants.CUSTOMER_SQL, able).get(), param.get());
            return result;
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> List<T> queryVOListByCustomElementName(Class<T> clazz, String elementId, Param param)
            throws DataAccessException {
        try {
            SqlSession session = getSession();
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(Constants.SQLConstants.CUSTOMER_SQL, elementId).get(), param.get()));
            return session.selectList(SQLCreator.set(Constants.SQLConstants.CUSTOMER_SQL, elementId).get(), param.get());
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }


    @Override
    public <T> int physicalWhereDelete(Class<T> clazz, CustomSQL whereSql) throws DataAccessException {
        try {
            SqlSession session = getSession();
            String whereSqlStr = whereSql.toString();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("whereSqlStr", whereSqlStr);
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.PHYSICALWHEREDELETE).get(), map));
            return session.delete(SQLCreator.set(clazz, ESQL.PHYSICALWHEREDELETE).get(), map);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> void insertBatch(Class<T> clazz, Throwable able, List list) throws DataAccessException {
        String key = SQLCreator.set(Constants.SQLConstants.CUSTOMER_SQL, able).get();

        SqlSession session = getSession();
        logger.debug("sql:[{}]", key, list);
        session.update(key, list);
    }

    @Override
    public <T> int updateCustomColumnByWhere(Class<T> clazz, Param param, CustomSQL whereSql)
            throws DataAccessException {
        try {
            SqlSession session = getSession();
            Map<String, Object> strMap = new HashMap<String, Object>();
            strMap.put(UPDATE_STRING, SQLUtil.updateSql(param));
            strMap.put(WHERE_STRING, whereSql.toString());
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.UPDATECUSTOMCOLUMNBYWHERE).get(), strMap));
            return session.update(SQLCreator.set(clazz, ESQL.UPDATECUSTOMCOLUMNBYWHERE).get(), strMap);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> int logicWhereDelete(Class<T> clazz, CustomSQL whereSql) throws DataAccessException {
        try {
            SqlSession session = getSession();
            String whereSqlStr = whereSql.toString();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("whereSqlStr", whereSqlStr);
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.LOGICWHEREDELETE).get(), map));
            return getSession().update(SQLCreator.set(clazz, ESQL.LOGICWHEREDELETE).get(), map);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> List<T> queryListResultByWhere(Class<T> clazz, CustomSQL whereSQL) throws DataAccessException {
        try {
            SqlSession session = getSession();
            String whereSqlStr = whereSQL.toString();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("whereSqlStr", whereSqlStr);
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.QUERYLISTRESULTBYWHERE).get(), map));
            return session.selectList(SQLCreator.set(clazz, ESQL.QUERYLISTRESULTBYWHERE).get(), map);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public <T> long queryListResultCountByWhere(Class<T> clazz, CustomSQL whereSQL) throws DataAccessException {
        try {
            SqlSession session = getSession();
            String whereSqlStr = whereSQL.toString();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("whereSqlStr", whereSqlStr);
            logger.debug("sql:[{}]", SQLUtil.getSql(session, SQLCreator.set(clazz, ESQL.QUERYLISTRESULTCOUNTBYWHERE).get(), map));
            return session.selectOne(SQLCreator.set(clazz, ESQL.QUERYLISTRESULTCOUNTBYWHERE).get(), map);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
