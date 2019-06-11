package genericCheckpointing.vistor;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public interface VisitorI {
	public void visit(MyAllTypesFirst first);
	public void visit(MyAllTypesSecond second);
}
