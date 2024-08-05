package com.invadermonky.magicultureintegrations.api.events;

public interface IConfigChangedEvent {
    default void onExternalConfigChanged(String modId) {}
}
