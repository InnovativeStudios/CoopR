#include "script_component.hpp"

class CfgPatches {
    class coopr_missions
    {
        // Meta information for editor
        name = "CoopR Mod";
        author = "xetra11";
        url = "https://github.com/CoopR-Mod/CoopR-Mod";

        requiredVersion = 1.80;
        requiredAddons[] = {
            "A3_3DEN",
            "coopr_core", // all addons need to depend on core
            "coopr_integration" // all addons need to depend on core
        };
        // List of objects (CfgVehicles classes) contained in the addon. Important also for Zeus content unlocking.
        units[] = {
        };
        // List of weapons (CfgWeapons classes) contained in the addon.
        weapons[] = {};
    };
}

#include "CfgFunctions.hpp"
#include "CfgVehicles.hpp"
#include "CfgSounds.hpp"
#include "CfgTaskDescriptions.hpp"

// UI
#include "\x\coopr\addons\core\coopr_gui_base.hpp"
#include "ui\CoopR_ReconReport_Dialog.hpp"
#include "ui\CoopR_MissionBoard_Dialog.hpp"
