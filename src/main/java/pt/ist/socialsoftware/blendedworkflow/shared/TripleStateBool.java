package pt.ist.socialsoftware.blendedworkflow.shared;

public enum TripleStateBool {
	TRUE,
	FALSE,
	SKIPPED;
	
	public TripleStateBool AND(TripleStateBool state) {
		if(this == SKIPPED || state == SKIPPED) {
			return SKIPPED;
		} else if(this == TRUE && state == TRUE) {
			return TRUE;
		}
		return FALSE;
	}
	
	public TripleStateBool OR(TripleStateBool state) {
	if(this == TRUE || state == TRUE) {
		return TRUE;
	} else if(this == SKIPPED && state != SKIPPED) {
		return SKIPPED;
	}
	return FALSE;
	}

	public TripleStateBool NOT() {
		if(this == TRUE) {
			return FALSE;
		} else if(this == FALSE) {
			return TRUE;
		}
		return SKIPPED;
	}
//
//	public static boolean equals(Object obj) {
//		if(!(obj instanceof TripleStateBool) && !(obj instanceof TripleStateBool.State)) {
//			return false;
//		}
//		if(obj instanceof TripleStateBool) {
//			return ((TripleStateBool) obj).get() == myState; 
//		}
//		return (TripleStateBool.State)obj == myState;
//	}

	
}

