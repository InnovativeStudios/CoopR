:: configure with own paths to MPMissions!!!
SET MPMissions="C:\Users\innov\Documents\Arma 3\MPMissions"
SET dest="D:\SteamLibrary\steamapps\common\Arma 3\x\coopr\demo_missions"

xcopy /E /H /R /Y %MPMissions%"\its.stratis" %dest%"\its.stratis\"
xcopy /E /H /R /Y %MPMissions%"\dev.vr\*" %dest%"\dev.vr\"
