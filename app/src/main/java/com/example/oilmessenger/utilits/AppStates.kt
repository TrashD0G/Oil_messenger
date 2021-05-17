package com.example.oilmessenger.utilits

enum class AppStates(val state: String) {
    ONLINE("в сети"),
    OFFLINE("не в сети");

    companion object {
        fun updateState(AppStates: AppStates){
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_STATE)
                .setValue(AppStates.state)
        }
    }
}
