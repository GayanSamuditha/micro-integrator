package org.wso2.esb.integration.common.clients.registry;

import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.registry.relations.stub.AddAssociationRegistryExceptionException;
import org.wso2.carbon.registry.relations.stub.GetAssociationTreeRegistryExceptionException;
import org.wso2.carbon.registry.relations.stub.GetDependenciesRegistryExceptionException;
import org.wso2.carbon.registry.relations.stub.RelationAdminServiceStub;
import org.wso2.carbon.registry.relations.stub.beans.xsd.AssociationTreeBean;
import org.wso2.carbon.registry.relations.stub.beans.xsd.DependenciesBean;
import org.wso2.esb.integration.common.clients.client.utils.AuthenticateStub;

import java.rmi.RemoteException;

public class RelationAdminServiceClient {

    private static final Log log = LogFactory.getLog(RelationAdminServiceClient.class);
    private final String serviceName = "RelationAdminService";
    private RelationAdminServiceStub relationAdminServiceStub;

    public RelationAdminServiceClient(String backendURL, String sessionCookie) throws AxisFault {
        String endPoint = backendURL + serviceName;
        relationAdminServiceStub = new RelationAdminServiceStub(endPoint);
        AuthenticateStub.authenticateStub(sessionCookie, relationAdminServiceStub);
    }

    public RelationAdminServiceClient(String backendURL, String userName, String password) throws AxisFault {
        String endPoint = backendURL + serviceName;
        relationAdminServiceStub = new RelationAdminServiceStub(endPoint);
        AuthenticateStub.authenticateStub(userName, password, relationAdminServiceStub);
    }

    public void addAssociation(String path, String type, String associationPath, String toDo)
            throws RemoteException, AddAssociationRegistryExceptionException {
        try {
            relationAdminServiceStub.addAssociation(path, type, associationPath, toDo);
        } catch (RemoteException e) {
            log.error("Add association error ");
            throw new RemoteException("Add association error ", e);
        } catch (AddAssociationRegistryExceptionException e) {
            log.error("Add association error ");
            throw new AddAssociationRegistryExceptionException("Add association error ", e);
        }
    }

    public DependenciesBean getDependencies(String path)
            throws RemoteException, AddAssociationRegistryExceptionException {
        DependenciesBean dependenciesBean = null;
        try {
            dependenciesBean = relationAdminServiceStub.getDependencies(path);
        } catch (RemoteException e) {
            log.error("Get dependencies error ");
            throw new RemoteException("Get dependencies error ", e);
        } catch (GetDependenciesRegistryExceptionException e) {
            log.error("Get dependencies error");
            throw new AddAssociationRegistryExceptionException("Get dependencies error ", e);
        }

        return dependenciesBean;
    }

    public AssociationTreeBean getAssociationTree(String path, String type)
            throws RemoteException, AddAssociationRegistryExceptionException {
        try {
            return relationAdminServiceStub.getAssociationTree(path, type);
        } catch (RemoteException e) {
            log.error("Get association tree error ");
            throw new RemoteException("Get association tree error ", e);
        } catch (GetAssociationTreeRegistryExceptionException e) {
            log.error("Get association tree error ");
            throw new AddAssociationRegistryExceptionException("Get association tree error ", e);
        }
    }
}
