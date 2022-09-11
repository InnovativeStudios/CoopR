#include "script_component.hpp"
/*
 * Author: xetra11
 *
 * Returns all entries for a given report ID
 *
 * Arguments:
 * _reportID <NUMBER> - ID of report
 *
 * Return Value:
 * _entires <ARRAY> - all found entries
 *
 * Example:
 * Trivial
 *
 * Public: No
 *
 * Scope: Server
 */

params [["_reportID", -1]];

if (isServer) then {
    if (_reportID isEqualTo -1) exitWith { ERROR("_reportID was not defined") };
    INFO2("fetching recon entries for report %1 ", _reportID);
    private _allEntries = format ["SELECT entry FROM recon_entries WHERE report_id = %1", _reportID];
    private _results = (_allEntries call coopr_fnc_extDB3sql);
    private _destructuredEntries = [];
    { _destructuredEntries pushBack (_x select 0)} forEach _results; // to keep intuitive array structures
    _destructuredEntries;
} else {
    SERVER_ONLY_ERROR(__FILE__);
};
