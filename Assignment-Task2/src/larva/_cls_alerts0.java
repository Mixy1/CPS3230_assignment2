package larva;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_alerts0 implements _callable{

public static PrintWriter pw; 
public static _cls_alerts0 root;

public static LinkedHashMap<_cls_alerts0,_cls_alerts0> _cls_alerts0_instances = new LinkedHashMap<_cls_alerts0,_cls_alerts0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\michael\\workspace\\Assignment-Task2/src/output_alerts.txt");

root = new _cls_alerts0();
_cls_alerts0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_alerts0 parent; //to remain null - this class does not have a parent!
public static int n;
int no_automata = 1;
 public int alerts =0 ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_alerts0() {
}

public void initialisation() {
}

public static _cls_alerts0 _get_cls_alerts0_inst() { synchronized(_cls_alerts0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_alerts0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_alerts0_instances){
_performLogic_verifyAlertLogic(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_alerts0[] a = new _cls_alerts0[1];
synchronized(_cls_alerts0_instances){
a = _cls_alerts0_instances.keySet().toArray(a);}
for (_cls_alerts0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_alerts0_instances){
_cls_alerts0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_verifyAlertLogic = 246;

public void _performLogic_verifyAlertLogic(String _info, int... _event) {

_cls_alerts0.pw.println("[verifyAlertLogic]AUTOMATON::> verifyAlertLogic("+") STATE::>"+ _string_verifyAlertLogic(_state_id_verifyAlertLogic, 0));
_cls_alerts0.pw.flush();

if (0==1){}
else if (_state_id_verifyAlertLogic==244){
		if (1==0){}
		else if ((_occurredEvent(_event,470/*purgeAlerts*/))){
		_cls_alerts0.pw .println ("alerts purged");

		_state_id_verifyAlertLogic = 246;//moving to state empty
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,474/*alertsReturnedByService*/)) && (n >5 )){
		_cls_alerts0.pw .println ("TOO MANY ALERTS");

		_state_id_verifyAlertLogic = 242;//moving to state tooMany
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,468/*createAlert*/)) && (alerts <4 )){
		alerts ++;
_cls_alerts0.pw .println ("Filling Alerts");

		_state_id_verifyAlertLogic = 244;//moving to state filling
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,468/*createAlert*/)) && (alerts ==4 )){
		alerts ++;
_cls_alerts0.pw .println ("Alerts full");

		_state_id_verifyAlertLogic = 245;//moving to state full
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,472/*invalidId*/))){
		_cls_alerts0.pw .println ("Invalid key");

		_state_id_verifyAlertLogic = 243;//moving to state invalid_key
		_goto_verifyAlertLogic(_info);
		}
}
else if (_state_id_verifyAlertLogic==243){
		if (1==0){}
		else if ((_occurredEvent(_event,470/*purgeAlerts*/))){
		_cls_alerts0.pw .println ("alerts purged");

		_state_id_verifyAlertLogic = 246;//moving to state empty
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,468/*createAlert*/)) && (alerts <4 )){
		alerts ++;
_cls_alerts0.pw .println ("Filling Alerts");

		_state_id_verifyAlertLogic = 244;//moving to state filling
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,468/*createAlert*/)) && (alerts ==4 )){
		alerts ++;
_cls_alerts0.pw .println ("Alerts full");

		_state_id_verifyAlertLogic = 245;//moving to state full
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,468/*createAlert*/)) && (alerts ==5 )){
		_cls_alerts0.pw .println ("Alerts full");

		_state_id_verifyAlertLogic = 245;//moving to state full
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,472/*invalidId*/))){
		_cls_alerts0.pw .println ("Invalid key");

		_state_id_verifyAlertLogic = 243;//moving to state invalid_key
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,474/*alertsReturnedByService*/)) && (n >5 )){
		_cls_alerts0.pw .println ("TOO MANY ALERTS");

		_state_id_verifyAlertLogic = 242;//moving to state tooMany
		_goto_verifyAlertLogic(_info);
		}
}
else if (_state_id_verifyAlertLogic==246){
		if (1==0){}
		else if ((_occurredEvent(_event,468/*createAlert*/))){
		alerts =1 ;
_cls_alerts0.pw .println ("Started filling Alerts");

		_state_id_verifyAlertLogic = 244;//moving to state filling
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,470/*purgeAlerts*/))){
		_cls_alerts0.pw .println ("No alerts to purge");

		_state_id_verifyAlertLogic = 246;//moving to state empty
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,474/*alertsReturnedByService*/)) && (n >5 )){
		_cls_alerts0.pw .println ("TOO MANY ALERTS");

		_state_id_verifyAlertLogic = 242;//moving to state tooMany
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,472/*invalidId*/))){
		_cls_alerts0.pw .println ("Invalid key");

		_state_id_verifyAlertLogic = 243;//moving to state invalid_key
		_goto_verifyAlertLogic(_info);
		}
}
else if (_state_id_verifyAlertLogic==245){
		if (1==0){}
		else if ((_occurredEvent(_event,468/*createAlert*/)) && (alerts ==5 )){
		_cls_alerts0.pw .println ("removing old alert");

		_state_id_verifyAlertLogic = 245;//moving to state full
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,470/*purgeAlerts*/))){
		_cls_alerts0.pw .println ("alerts purged");

		_state_id_verifyAlertLogic = 246;//moving to state empty
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,472/*invalidId*/))){
		_cls_alerts0.pw .println ("Invalid key");

		_state_id_verifyAlertLogic = 243;//moving to state invalid_key
		_goto_verifyAlertLogic(_info);
		}
		else if ((_occurredEvent(_event,474/*alertsReturnedByService*/)) && (n >5 )){
		_cls_alerts0.pw .println ("TOO MANY ALERTS");

		_state_id_verifyAlertLogic = 242;//moving to state tooMany
		_goto_verifyAlertLogic(_info);
		}
}
}

public void _goto_verifyAlertLogic(String _info){
_cls_alerts0.pw.println("[verifyAlertLogic]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_verifyAlertLogic(_state_id_verifyAlertLogic, 1));
_cls_alerts0.pw.flush();
}

public String _string_verifyAlertLogic(int _state_id, int _mode){
switch(_state_id){
case 244: if (_mode == 0) return "filling"; else return "filling";
case 243: if (_mode == 0) return "invalid_key"; else return "invalid_key";
case 242: if (_mode == 0) return "tooMany"; else return "!!!SYSTEM REACHED BAD STATE!!! tooMany "+new _BadStateExceptionalerts().toString()+" ";
case 245: if (_mode == 0) return "full"; else return "full";
case 246: if (_mode == 0) return "empty"; else return "empty";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}