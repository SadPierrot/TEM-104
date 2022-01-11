package com.company.dto;

import java.time.LocalDateTime;

public class FlashSysIntDto {
    private LocalDateTime timeCurrentEntry;
    private LocalDateTime timePreviousEntry;
    private float[] fractionalVolumeIntegrators;
    private float[] fractionalMassIntegrators;
    private float[] fractionalEnergyIntegrators;
    private float[] wholeVolumeIntegrators;
    private float[] wholeMassIntegrators;
    private float[] wholeEnergyIntegrators;
    private float timeInEnergy;
    private float[] timeWithoutErrors;
    private float[] lessThanMinimum;
    private float[] lessThanMaximum;
    private float[] temperatureLessMinimum;
    private float[] technicalMalfunction;
    private byte[] systemErrorsFirst;
    private short[] systemErrorsSecond;
    private short[] systemTemperature;
    private byte[] systemPressure;
    private float[] channelFlowIntegrators;

    public FlashSysIntDto(LocalDateTime timeCurrentEntry, LocalDateTime timePreviousEntry, float[] fractionalVolumeIntegrators, float[] fractionalMassIntegrators, float[] fractionalEnergyIntegrators, float[] wholeVolumeIntegrators, float[] wholeMassIntegrators, float[] wholeEnergyIntegrators, float timeInEnergy, float[] timeWithoutErrors, float[] lessThanMinimum, float[] lessThanMaximum, float[] temperatureLessMinimum, float[] technicalMalfunction, byte[] systemErrorsFirst, short[] systemErrorsSecond, short[] systemTemperature, byte[] systemPressure, float[] channelFlowIntegrators) {
        this.timeCurrentEntry = timeCurrentEntry;
        this.timePreviousEntry = timePreviousEntry;
        this.fractionalVolumeIntegrators = fractionalVolumeIntegrators;
        this.fractionalMassIntegrators = fractionalMassIntegrators;
        this.fractionalEnergyIntegrators = fractionalEnergyIntegrators;
        this.wholeVolumeIntegrators = wholeVolumeIntegrators;
        this.wholeMassIntegrators = wholeMassIntegrators;
        this.wholeEnergyIntegrators = wholeEnergyIntegrators;
        this.timeInEnergy = timeInEnergy;
        this.timeWithoutErrors = timeWithoutErrors;
        this.lessThanMinimum = lessThanMinimum;
        this.lessThanMaximum = lessThanMaximum;
        this.temperatureLessMinimum = temperatureLessMinimum;
        this.technicalMalfunction = technicalMalfunction;
        this.systemErrorsFirst = systemErrorsFirst;
        this.systemErrorsSecond = systemErrorsSecond;
        this.systemTemperature = systemTemperature;
        this.systemPressure = systemPressure;
        this.channelFlowIntegrators = channelFlowIntegrators;
    }

    public LocalDateTime getTimeCurrentEntry() {
        return timeCurrentEntry;
    }

    public void setTimeCurrentEntry(LocalDateTime timeCurrentEntry) {
        this.timeCurrentEntry = timeCurrentEntry;
    }

    public LocalDateTime getTimePreviousEntry() {
        return timePreviousEntry;
    }

    public void setTimePreviousEntry(LocalDateTime timePreviousEntry) {
        this.timePreviousEntry = timePreviousEntry;
    }

    public float[] getFractionalVolumeIntegrators() {
        return fractionalVolumeIntegrators;
    }

    public void setFractionalVolumeIntegrators(float[] fractionalVolumeIntegrators) {
        this.fractionalVolumeIntegrators = fractionalVolumeIntegrators;
    }

    public float[] getFractionalMassIntegrators() {
        return fractionalMassIntegrators;
    }

    public void setFractionalMassIntegrators(float[] fractionalMassIntegrators) {
        this.fractionalMassIntegrators = fractionalMassIntegrators;
    }

    public float[] getFractionalEnergyIntegrators() {
        return fractionalEnergyIntegrators;
    }

    public void setFractionalEnergyIntegrators(float[] fractionalEnergyIntegrators) {
        this.fractionalEnergyIntegrators = fractionalEnergyIntegrators;
    }

    public float[] getWholeVolumeIntegrators() {
        return wholeVolumeIntegrators;
    }

    public void setWholeVolumeIntegrators(float[] wholeVolumeIntegrators) {
        this.wholeVolumeIntegrators = wholeVolumeIntegrators;
    }

    public float[] getWholeMassIntegrators() {
        return wholeMassIntegrators;
    }

    public void setWholeMassIntegrators(float[] wholeMassIntegrators) {
        this.wholeMassIntegrators = wholeMassIntegrators;
    }

    public float[] getWholeEnergyIntegrators() {
        return wholeEnergyIntegrators;
    }

    public void setWholeEnergyIntegrators(float[] wholeEnergyIntegrators) {
        this.wholeEnergyIntegrators = wholeEnergyIntegrators;
    }

    public float getTimeInEnergy() {
        return timeInEnergy;
    }

    public void setTimeInEnergy(float timeInEnergy) {
        this.timeInEnergy = timeInEnergy;
    }

    public float[] getTimeWithoutErrors() {
        return timeWithoutErrors;
    }

    public void setTimeWithoutErrors(float[] timeWithoutErrors) {
        this.timeWithoutErrors = timeWithoutErrors;
    }

    public float[] getLessThanMinimum() {
        return lessThanMinimum;
    }

    public void setLessThanMinimum(float[] lessThanMinimum) {
        this.lessThanMinimum = lessThanMinimum;
    }

    public float[] getLessThanMaximum() {
        return lessThanMaximum;
    }

    public void setLessThanMaximum(float[] lessThanMaximum) {
        this.lessThanMaximum = lessThanMaximum;
    }

    public float[] getTemperatureLessMinimum() {
        return temperatureLessMinimum;
    }

    public void setTemperatureLessMinimum(float[] temperatureLessMinimum) {
        this.temperatureLessMinimum = temperatureLessMinimum;
    }

    public float[] getTechnicalMalfunction() {
        return technicalMalfunction;
    }

    public void setTechnicalMalfunction(float[] technicalMalfunction) {
        this.technicalMalfunction = technicalMalfunction;
    }

    public byte[] getSystemErrorsFirst() {
        return systemErrorsFirst;
    }

    public void setSystemErrorsFirst(byte[] systemErrorsFirst) {
        this.systemErrorsFirst = systemErrorsFirst;
    }

    public short[] getSystemErrorsSecond() {
        return systemErrorsSecond;
    }

    public void setSystemErrorsSecond(short[] systemErrorsSecond) {
        this.systemErrorsSecond = systemErrorsSecond;
    }

    public short[] getSystemTemperature() {
        return systemTemperature;
    }

    public void setSystemTemperature(short[] systemTemperature) {
        this.systemTemperature = systemTemperature;
    }

    public byte[] getSystemPressure() {
        return systemPressure;
    }

    public void setSystemPressure(byte[] systemPressure) {
        this.systemPressure = systemPressure;
    }

    public float[] getChannelFlowIntegrators() {
        return channelFlowIntegrators;
    }

    public void setChannelFlowIntegrators(float[] channelFlowIntegrators) {
        this.channelFlowIntegrators = channelFlowIntegrators;
    }
}
