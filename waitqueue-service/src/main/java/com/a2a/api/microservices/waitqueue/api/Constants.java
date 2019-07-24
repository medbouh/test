package com.a2a.api.microservices.waitqueue.api;

/**
 * Constantes d'URIs pour les appels aux apis
 *
 * @author mohamed
 */
public final class Constants {

    static final String DEPARTMENT_ID = "departmentId";

    static final String ADD_TO_WAITQUEUE = "/add/{departmentId}";

    static final String DELETE_TO_WAITQUEUE = "/delete/{departmentId}";

    static final String LAST_NUMBER_OF_WAITQUEUE = "/lastNumber/{departmentId}";

    static final String INCREASE_LAST_NUMBER_OF_WAITQUEUE = "/increaseLastNumber/{departmentId}";

    static final String INCREASE_INDEX_OF_WAITQUEUE = "/increaseIndex/{departmentId}";


}
