# Home screen implementation spec

## Layout (16:9 TV)

1. **Canvas**
   - Full-bleed 16:9 content surface; deep near-black navy background.
   - 5–6% horizontal safe margin.
   - Keep enough bottom gradient behind the first catalog row for legibility.

2. **Navigation rail**
   - 64–88 dp compact resting width; expands to 190–220 dp while selected/expanded if this fits the existing Nuvio navigation model.
   - Home, Movies, Series, Search, Library, Settings.
   - Clear selected marker; high-contrast icon and label when focused.
   - D-pad Up/Down moves within the rail; Right enters the content area; Left from the first content focus returns to the rail.
   - Keep the rail out of the way of hero artwork.

3. **Hero panel**
   - Occupies roughly the upper 48–58% of available screen height.
   - Backdrop image fills the area using a crop; apply a horizontal left-to-right and bottom scrim.
   - Left-aligned title (large), year/runtime/genres/rating metadata where available, a two-line synopsis maximum, and two actions: Play and Details.
   - D-pad focus on Play / Details uses a visible shape/color change. No autoplay trailer in MVP.

4. **Continue Watching**
   - Appears below the hero when there are resumable items.
   - Use real Nuvio continue-watching state; progress indicator should be visible and accessible.
   - If there are no in-progress titles, omit the row rather than rendering fake content.

5. **Catalog rows**
   - Horizontal rail with heading, poster card grid (target 126–150 dp wide, aspect ratio near 2:3 unless catalog metadata says landscape), consistent spacing.
   - Focused item scales modestly (around 1.05–1.08x), has an accessible outline, and is not clipped by parent layout.
   - Poster title below or overlayed depending on the style toggle; avoid layout jumps during focus changes.
   - Optional See All action at the row end only if supported by the current catalog navigation.

6. **States**
   - Initial load: skeleton rows; do not flash an empty-state message before provider/catalog loading completes.
   - No sources/catalogs: informative empty state and direct Settings/Add-ons action.
   - Network error: retry affordance; keep previously loaded rows if Nuvio's state supports that.
   - Missing backdrop/poster/logo: local placeholder, never broken-image UI.

## Visual language

- Background: near-black with subtle blue/indigo tone.
- Surfaces: low-contrast charcoal/blue-gray.
- Primary text: warm white; secondary metadata: muted cool gray.
- Accent: a single saturated accent such as coral or amber for primary actions and selected rail marker.
- Corners: moderate radius, consistent across cards, buttons and dialogs.
- Motion: short fades/scale transitions; avoid constant animated movement and motion that interferes with D-pad selection.

This is an independent visual style direction, not an exact reproduction of Chillio's copyrighted assets.

## Accessibility and remote behavior

- All interactive elements have predictable focus order and visible focus.
- Every action is reachable with a D-pad and Select/Enter.
- Back returns to the previous screen without resetting the catalog row/selected item unnecessarily.
- Long-press and context actions must not be the only way to reach critical features.
- Test at 720p and 1080p and confirm system overscan / TV safe area.

## Implementation sequence

1. Inspect existing Nuvio home composables and keep view-model data contracts.
2. Build visual components using existing `MetaPreview`, catalog row, hero and continue-watching models.
3. Integrate one row at a time. Do not replace existing playback or account logic.
4. Build with `./gradlew :app:assembleFullDebug`.
5. Run on an Android TV emulator/device and test D-pad navigation, backstack, and playback.
