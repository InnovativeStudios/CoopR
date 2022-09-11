#include "script_component.hpp"

INFO2("player %1 has been killed in action", name player);
cutText ["You have been wounded in action", "BLACK OUT", 0.1];

// setting variables for wounded in action state
player setVariable [COOPR_CHAR_STATE, COOPR_STATE_WIA, true];
player setVariable [COOPR_CHAR_WOUNDED_TIMESTAMP, call coopr_fnc_currentGameTime, true];
player setVariable [COOPR_CHAR_POSITION, getPos COOPR_HQ_WEST, true];

COOPR_WIA_REDUCTION call coopr_fnc_convertTempToReputation;



