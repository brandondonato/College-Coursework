package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;

public interface DeserStrategy {
	SerializableObject processOutput(StoreRestoreHandler reader);
}
