package com.guess_apps.advancedandroid.details;

import com.google.auto.value.AutoValue;
import com.guess_apps.advancedandroid.model.Contributor;

import java.util.List;

import javax.annotation.Nullable;

@AutoValue
abstract class ContributorState {

    abstract boolean loading();

    @Nullable
    abstract Integer errorRes();

    boolean isSuccess() {
        return errorRes() == null;
    }
    static Builder builder() {
        return new AutoValue_ContributorState.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {

        abstract Builder loading(boolean loading);

        abstract Builder errorRes(Integer errorRes);

        abstract ContributorState build();
    }
}
