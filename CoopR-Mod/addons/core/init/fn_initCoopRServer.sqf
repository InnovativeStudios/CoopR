#include "script_component.hpp"
/*
 * Author: xetra11
 *
 * First script logic that is run to initialize the CoopR Server base logic.
 *
 * Arguments:
 * None
 *
 * Return Value:
 * None
 *
 * Public: No
 *
 * Scope: Server
 */

if (isServer) then {
    INFO2("CoopR Version: %1 ", COOPR_VERSION);
    private _setupValid = call coopr_fnc_verifySetup;
    if(_setupValid) then {
        call coopr_fnc_initEventsServer;
        //call coopr_fnc_staticData;
        true call coopr_fnc_sync;
        //wait until the persistence module finished its setup
        [] spawn {
            call coopr_fnc_initPersistence;
            call coopr_fnc_initServerMeta;
            [] remoteExec ["coopr_fnc_initCoopRClients", EXEC_GLOBAL, true];
            call coopr_fnc_initServerRoutines;
            INFO("server initialized");
        };
    } else {
        ERROR("Server was not initialized. Setup verification failed");
    };
} else {
    //SERVER_ONLY_ERROR(__FILE__);
}

