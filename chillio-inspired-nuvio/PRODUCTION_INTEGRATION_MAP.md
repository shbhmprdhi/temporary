# Production integration map

This file records the source-backed integration points for the UI prototypes. It is not a claim that the prototype is already wired into Nuvio.

## Home

Production entry point:
- `app/src/main/java/com/nuvio/tv/ui/screens/home/HomeScreen.kt`
- `HomeScreen(...)` already receives callbacks for details, continue watching, catalog "see all", and collection folders.
- It observes `HomeUiState`, respects startup/loading gates, and selects Classic, Grid, or Modern presentation.
- `HomeUiState.kt` already exposes `catalogRows`, `continueWatchingItems`, `heroItems`, `homeRows`, layout preferences, watched status, and library membership state.
- `ModernHomePresentation.kt` builds stable presentation rows for Continue Watching, upcoming items, catalogs, and collections.

## Safe adaptation rules

1. Keep `HomeScreen` as the route entry point; do not add a second ViewModel for the same data.
2. Use the existing profile/account/navigation state from the app shell for the top bar. Do not hard-code a greeting name, date, or account count.
3. Map real catalog and Continue Watching entries into presentation components. Do not infer Favorites from arbitrary catalog data; find the existing library/favorites source and use it.
4. Keep the existing `onNavigateToDetail`, `onContinueWatchingClick`, `onNavigateToCatalogSeeAll`, and `onNavigateToFolderDetail` callbacks. Continue Watching should use its existing callback so progress/playback behavior remains intact.
5. Make TV focus behavior explicit: focused border/scale, predictable D-pad traversal, focus restoration after details/back, and no touch-only interaction assumptions.
6. Preserve the startup loader/error/empty states, long-press poster actions, watched state, lazy catalog loading, and scroll restoration already implemented by Nuvio.
7. Use the app's existing Coil 3 image loading and Nuvio theme tokens in the production integration; avoid adding another image loader or a parallel visual theme.

## Detail screen

The exact production detail route/file still needs to be located and read before integrating `ChillioInspiredDetail.kt`. Do not wire favorite, watched, trailer, or playback buttons to no-op callbacks. Each action must use the existing detail ViewModel/repository/route contract.

## Build validation

The upstream README documents:
```bash
./gradlew :app:assembleFullDebug
```

Build must run from a complete Nuvio checkout with Android SDK/JDK and the project's expected local configuration. This scratch repository is not a full source checkout, so compiling these standalone prototype files here would not validate integration.

## Current limitation

The current connected GitHub access can read upstream Nuvio source but cannot push there, and no fork is available in the connected repository list. The prototypes remain isolated in the scratch branch until a writable Nuvio fork/checkout is available.
