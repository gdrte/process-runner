package com.cpn.vsp.processrunner;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.cpn.vsp.vertexcore.RemoteExecutor;

/**
 * Executes only a limited set of commands.
 * @author gdr
 *
 */
public class ProcessEngine extends UnicastRemoteObject implements RemoteExecutor{
	protected ProcessEngine() throws RemoteException {
		super();
	}
	private static final long serialVersionUID = -8291571543207697601L;
	
	String command="openssl";
	public int exec(String...remoteArgs) throws RemoteException {
		return CLibrary.INSTANCE.system(command+" "+concate(remoteArgs));
	}

	private String concate(String[] args){
		String s="";
		for(String arg : args){
			if(arg.startsWith(";")){
				continue;
			}
			s+=arg+" ";
		}
		return s;
	}
	
	public static void main(String[] args) {
        try {
            String name = "rExec";
            RemoteExecutor engine = new ProcessEngine();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(name, engine);
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
	
}
