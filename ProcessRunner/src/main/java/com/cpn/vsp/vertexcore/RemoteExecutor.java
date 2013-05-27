package com.cpn.vsp.vertexcore;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteExecutor extends Remote {
	public int exec(final String ...remoteArgs) throws RemoteException;
}
