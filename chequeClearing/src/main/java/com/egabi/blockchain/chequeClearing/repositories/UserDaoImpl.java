//package com.demos.haleem.batchproject.repositories;
//
//
//import PortalUser;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class UserDaoImpl implements UserDao{
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @SuppressWarnings("unchecked")
//    public PortalUser findByUserName(String username) {
//
//        List<PortalUser> portalUsers = new ArrayList<PortalUser>();
//
//        portalUsers = sessionFactory.getCurrentSession()
//                .createQuery("from User where username=?")
//                .setParameter(0, username)
//                .list();
//
//        if (portalUsers.size() > 0) {
//            return portalUsers.get(0);
//        } else {
//            return null;
//        }
//
//    }
//}
