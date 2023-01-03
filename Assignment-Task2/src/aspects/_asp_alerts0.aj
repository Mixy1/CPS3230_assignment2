package aspects;

import larva.*;
public aspect _asp_alerts0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_alerts0.initialize();
}
}
before () : (call(* *.invalidId(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alerts0.lock){

_cls_alerts0 _cls_inst = _cls_alerts0._get_cls_alerts0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 472/*invalidId*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 472/*invalidId*/);
}
}
before () : (call(* *.createAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alerts0.lock){

_cls_alerts0 _cls_inst = _cls_alerts0._get_cls_alerts0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 468/*createAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 468/*createAlert*/);
}
}
before ( int n) : (call(* *.alertsReturnedByService(..)) && args(n) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alerts0.lock){

_cls_alerts0 _cls_inst = _cls_alerts0._get_cls_alerts0_inst();
_cls_inst.n = n;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 474/*alertsReturnedByService*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 474/*alertsReturnedByService*/);
}
}
before () : (call(* *.purgeAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alerts0.lock){

_cls_alerts0 _cls_inst = _cls_alerts0._get_cls_alerts0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 470/*purgeAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 470/*purgeAlerts*/);
}
}
}