# Chillio-inspired Nuvio TV home screen

This folder contains an implementation brief for a personal Android TV UI experiment based on a clean-room visual study of the provided Chillio TV APK. It does not include Chillio source code or proprietary assets.

## Goal

Rework the Nuvio TV home screen's presentation while keeping Nuvio's catalog, details, and playback implementation intact.

## What static APK analysis confirmed

APK metadata: package `app.lumoslabs.chillio`, version `1.4.0`.

Readable strings and class names indicate:
- Jetpack Compose UI and a `MainActivity`.
- A home screen with Featured content and a discovery endpoint at `v1/media/discovery`.
- Continue-watching content.
- User preferences for hidden discovery sections, selected home tab, poster labels, year, and rating overlays.
- A Chillio account / profile system and purchase-related identifiers.

Because this is static APK analysis with obfuscated resource filenames, it does not reveal exact pixel geometry, dynamic focus transitions, or the full runtime navigation behavior. Running the app and capturing screenshots would be the next accuracy step.

## Proposed home-screen treatment

- Dark, high-contrast canvas with a generous cinematic hero.
- Compact left rail with clear icon + label states.
- Hero title, metadata, and primary Play / Details actions.
- Horizontal catalog rows with poster art, predictable D-pad focus, clear focused outline/scale, and remembered scroll position.
- Continue Watching row retaining playback progress data.
- Proper loading skeleton, no-catalog state, image fallback, and retry/error state.

All visual assets must be original, user-provided, or appropriately licensed. Use generic placeholder imagery while building the layout.

## Nuvio integration strategy

The upstream project is `https://github.com/NuvioMedia/NuvioTV`, whose current `dev` branch README describes Kotlin, Jetpack Compose, TV Material 3, Media3 and the build command:

```bash
./gradlew :app:assembleFullDebug
```

The preferred integration is to adapt the existing Nuvio `HomeScreen` and related presentation components instead of adding a second player or duplicating catalog logic.

Relevant upstream files to inspect:
- `app/src/main/java/com/nuvio/tv/ui/screens/home/HomeScreen.kt`
- `app/src/main/java/com/nuvio/tv/ui/screens/home/HomeUiState.kt`
- `app/src/main/java/com/nuvio/tv/ui/screens/home/ModernHomePresentation.kt`

The upstream Nuvio repository is not writable with the currently connected GitHub permission, so this brief lives in a separate user-owned repository. It is not a fork or a compiled APK.

## License notes

Nuvio TV is GPL-3.0. If you later modify and distribute a Nuvio-derived app, preserve the applicable license notices and comply with the license obligations. This plan does not use copied Chillio code or images.
