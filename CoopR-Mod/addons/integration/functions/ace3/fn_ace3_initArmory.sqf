#include "script_component.hpp"
params ["_armory"];

//TODO: rename equipment vendor shop to armory
[_armory, 0, ["ACE_MainActions"],
["armory_action_1", localize "str.coopr.equipment.action.armory", "", {call coopr_fnc_showArmoryDialog}, {true}] call ace_interact_menu_fnc_createAction
] call ace_interact_menu_fnc_addActionToObject;
DEBUG2("initialized %1 as armory (ACE3)", _armory);
