# Screenshot-based Chillio UI reference analysis

The user supplied two screenshots from Chillio's public website/product imagery. Use these as the primary visual references over the earlier generic prototype.

## Screen A — media details
- Full-bleed landscape backdrop, image fills the upper screen and fades into a dark tinted gradient.
- Title/logo anchored left, lower than the top edge; large high-contrast type.
- Main action row: white pill Play button and a secondary favorite/heart control.
- Separate wide secondary action for Mark as Watched.
- Metadata and truncated synopsis sit alongside/below the actions.
- Ratings are a dedicated horizontal band with multiple providers, icons, score/percentage, and vote counts.
- Trailers row begins below the ratings band.
- Keep text legible over the image using multi-stop gradients; backdrop remains visible at the top.

## Screen B — home/library
- Dark, near-black canvas with subtle diffuse color behind the content.
- Profile/avatar and greeting in the top-left; segmented Search / Movies / Shows / Live / Settings navigation near the top center; date and active-account status on the right.
- Large, rounded genre/category tiles near the top with image collage on the right side of each tile.
- Continue Watching is a compact card treatment with title art, play affordance, progress bar, and remaining/runtime text.
- Favorites uses a horizontal poster rail with generous spacing and rounded corners.
- Composition is calm and spacious, with large horizontal margins and no persistent left-side navigation rail in this reference.

## Implementation implications for Nuvio
- Prefer a top segmented navigation pattern and profile block for the target home screen, rather than the left rail in the earlier `HOME_SCREEN_PREVIEW.html` mockup.
- Keep the hero/detail layout responsive to landscape backdrops and maintain a clear gradient behind controls.
- Add ratings as a modular horizontal section only where source data is available. Avoid fabricating Rotten Tomatoes, Metascore, IMDb, or TMDB scores; show only supported sources.
- Category cards should use Nuvio-supported content and licensed art; if unavailable, use simple original gradients/placeholders.
- TV D-pad focus states, focus restoration, and scroll behavior must be implemented natively in Compose and tested on-device.

## Public website context
- `https://chillio.app/` documents the product and feature set.
- `https://link.chillio.app/` is the ChillLink addon/feed integration platform. It describes `/feeds` and `/streams`, a feed builder, and a developer toolkit. It is not the same as the account management portal or play portal mentioned in the product's v6 announcement.

These are reference observations, not an exact pixel specification. The supplied screenshots are the visual source of truth for the intended direction.