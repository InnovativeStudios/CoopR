#include "script_component.hpp"
/*
 * Author: Gilles
 *
 * This function checks if the player is able to build the structure given in
 * the parameter. Useful for showing/hiding the action in the action menu. 
 *
 * Arguments:
 * 0: _structure <STRING>, The structure the player could build.
 *
 * Return Value:
 * None
 *
 * Example:
 *
 * Public: No
 *
 * Scope: Client
 */

params[["_structure", UNDEFINED]];
if(_structure isEqualTo UNDEFINED) then{
	ERROR("Required parameter undefined: _structure");
};

switch _structure do{
	case COOPR_CAMP_ITEM_AREA: {
		if(!(player getVariable ["coopr_campingItem_0", false])) exitWith {false};
		if(!isFormationLeader player) exitWith{false};
		true;
	};
	case COOPR_CAMP_ITEM_TENT: {
		private _condition = call coopr_fnc_isInsideCampingArea;
		_condition;
		};
};