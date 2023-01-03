package aspects;

import larva.*;
public aspect _asp_logins0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_logins0.initialize();
}
}
before () : (call(* *.invalidId(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_logins0.lock){

_cls_logins0 _cls_inst = _cls_logins0._get_cls_logins0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 418/*invalidId*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 418/*invalidId*/);
}
}
before () : (call(* *.validLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_logins0.lock){

_cls_logins0 _cls_inst = _cls_logins0._get_cls_logins0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 414/*validLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 414/*validLogin*/);
}
}
before () : (call(* *.logout(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_logins0.lock){

_cls_logins0 _cls_inst = _cls_logins0._get_cls_logins0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 416/*logout*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 416/*logout*/);
}
}
}