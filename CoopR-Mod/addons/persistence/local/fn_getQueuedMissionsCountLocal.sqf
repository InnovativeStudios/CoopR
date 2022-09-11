#include "script_component.hpp"
/*
 * Author: xetra11
 *
 * Counts all queued missions
 *
 * Arguments:
 * 0: _serverID <NUMBER> - server id to filter count
 *
 * Return Value:
 * _missions <ARRAY> - all found missions for server id
 *
 * Example:
 * Trivial
 *
 * Public: No
 *
 * Scope: Server
 */
params [["_serverID", -1]];

if (isServer) then {
    private _count = "";
    if (_serverID isEqualTo -1) then {
        _count = format["SELECT count(*) FROM mission_queues"];
    } else {
        DEBUG2("server id in count queue: %1", _serverID);
        _count = format["SELECT count(*) FROM mission_queues WHERE server_id = %1", _serverID];
    };

    (_count call coopr_fnc_extDB3sql) select 0 select 0;
} else {
    SERVER_ONLY_ERROR(__FILE__);
};
