#include "script_component.hpp"
/*
 * Author: xetra11
 *
 * Removes queued mission at given ID on local
 *
 * Arguments:
 * _id <NUMBER> - ID of queued coopr mission to be removed
 *
 * Return Value:
 * None
 *
 * Example:
 * Trivial
 *
 * Public: No
 *
 * Scope: Server
 */

params [["_id", -1]];

if (isServer) then {
    if (_id isEqualTo -1) exitWith { ERROR("_id was not defined") };

    private _deleteRow = format ["DELETE FROM mission_queues WHERE id = %1;", _id];
    _deleteRow call coopr_fnc_extDB3sql;
} else {
    SERVER_ONLY_ERROR(__FILE__);
};
