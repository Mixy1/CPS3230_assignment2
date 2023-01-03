package larva;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_logins0 implements _callable{

public static PrintWriter pw; 
public static _cls_logins0 root;

public static LinkedHashMap<_cls_logins0,_cls_logins0> _cls_logins0_instances = new LinkedHashMap<_cls_logins0,_cls_logins0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\michael\\workspace\\Assignment-Task2/src/output_logins.txt");

root = new _cls_logins0();
_cls_logins0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_logins0 parent; //to remain null - this class does not have a parent!
int no_automata = 1;
 public boolean logged_in =false ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_logins0() {
}

public void initialisation() {
}

public static _cls_logins0 _get_cls_logins0_inst() { synchronized(_cls_logins0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_logins0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_logins0_instances){
_performLogic_verifySystemLogic(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_logins0[] a = new _cls_logins0[1];
synchronized(_cls_logins0_instances){
a = _cls_logins0_instances.keySet().toArray(a);}
for (_cls_logins0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_logins0_instances){
_cls_logins0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_verifySystemLogic = 211;

public void _performLogic_verifySystemLogic(String _info, int... _event) {

_cls_logins0.pw.println("[verifySystemLogic]AUTOMATON::> verifySystemLogic("+") STATE::>"+ _string_verifySystemLogic(_state_id_verifySystemLogic, 0));
_cls_logins0.pw.flush();

if (0==1){}
else if (_state_id_verifySystemLogic==210){
		if (1==0){}
		else if ((_occurredEvent(_event,416/*logout*/))){
		logged_in =false ;
_cls_logins0.pw .println ("Logged out");

		_state_id_verifySystemLogic = 211;//moving to state logged_out
		_goto_verifySystemLogic(_info);
		}
		else if ((_occurredEvent(_event,414/*validLogin*/))){
		logged_in =true ;
_cls_logins0.pw .println ("Logged in");

		_state_id_verifySystemLogic = 209;//moving to state logged_in
		_goto_verifySystemLogic(_info);
		}
		else if ((_occurredEvent(_event,418/*invalidId*/))){
		logged_in =false ;
_cls_logins0.pw .println ("Invalid key");

		_state_id_verifySystemLogic = 210;//moving to state invalid_key
		_goto_verifySystemLogic(_info);
		}
}
else if (_state_id_verifySystemLogic==209){
		if (1==0){}
		else if ((_occurredEvent(_event,416/*logout*/))){
		logged_in =false ;
_cls_logins0.pw .println ("Logged out");

		_state_id_verifySystemLogic = 211;//moving to state logged_out
		_goto_verifySystemLogic(_info);
		}
		else if ((_occurredEvent(_event,414/*validLogin*/))){
		logged_in =true ;
_cls_logins0.pw .println ("Logged in");

		_state_id_verifySystemLogic = 209;//moving to state logged_in
		_goto_verifySystemLogic(_info);
		}
		else if ((_occurredEvent(_event,418/*invalidId*/))){
		logged_in =false ;
_cls_logins0.pw .println ("Invalid key");

		_state_id_verifySystemLogic = 210;//moving to state invalid_key
		_goto_verifySystemLogic(_info);
		}
}
else if (_state_id_verifySystemLogic==211){
		if (1==0){}
		else if ((_occurredEvent(_event,416/*logout*/))){
		logged_in =false ;
_cls_logins0.pw .println ("Logged out");

		_state_id_verifySystemLogic = 211;//moving to state logged_out
		_goto_verifySystemLogic(_info);
		}
		else if ((_occurredEvent(_event,414/*validLogin*/))){
		logged_in =true ;
_cls_logins0.pw .println ("Logged in");

		_state_id_verifySystemLogic = 209;//moving to state logged_in
		_goto_verifySystemLogic(_info);
		}
		else if ((_occurredEvent(_event,418/*invalidId*/))){
		logged_in =false ;
_cls_logins0.pw .println ("Invalid key");

		_state_id_verifySystemLogic = 210;//moving to state invalid_key
		_goto_verifySystemLogic(_info);
		}
}
}

public void _goto_verifySystemLogic(String _info){
_cls_logins0.pw.println("[verifySystemLogic]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_verifySystemLogic(_state_id_verifySystemLogic, 1));
_cls_logins0.pw.flush();
}

public String _string_verifySystemLogic(int _state_id, int _mode){
switch(_state_id){
case 210: if (_mode == 0) return "invalid_key"; else return "invalid_key";
case 209: if (_mode == 0) return "logged_in"; else return "logged_in";
case 211: if (_mode == 0) return "logged_out"; else return "logged_out";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}