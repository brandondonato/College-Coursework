package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;

public interface SerStrategy {
	void processInput(SerializableObject sObject, StoreRestoreHandler writer);
}
