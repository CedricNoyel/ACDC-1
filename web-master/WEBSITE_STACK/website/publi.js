var api_url = "https://api.archives-ouvertes.fr/search/?wt=json&rows=10000&fq=structure_t:(ASCOLA%20OR%20STACK)&fl=docid,uri_s,label_s,authFullName_s,title_s,producedDateY_i,files_s,conferenceTitle_s,journalTitle_s&sort=producedDateY_i%20desc";

var publications_period = "";

function updatePublications (publications) {
    let html = `
<p>${publications.response.numFound} publications${publications_period}.</p>
<ul class="articles">
`;
    for (let p of publications.response.docs) {
        html +=`
<li>
  <h4><a href="${p.uri_s}">${p.title_s[0]}</a></h4>
  <p>${p.authFullName_s.join(", ")} (${p.producedDateY_i})</p>
  <p class="conference">${"conferenceTitle_s" in p ? p.conferenceTitle_s : ""}</p>
  <div class="files">
`;
        if ("files_s" in p) {
            html += `<a href="${p.files_s[0]}">PDF</a>`;
        }
        html += `
    <a href="${p.uri_s}/bibtex">Bibtex</a>
  </div>
</li>
`;
    }
    html += "</ul>";
    $('#hal_target').html(html);
    return html;
}

// producedDateY_i vs releasedDateY_i

function getPublications () {
    publications_period = "";
    $('#hal_target').html(`<p>Loading publications${publications_period}...</p>`);
    $.getJSON(api_url, updatePublications);
}

function getPublicationsYear (start_year, end_year) {
    publications_period = (start_year == end_year ? (" in "+start_year) : (" from "+start_year+" to "+end_year));
    $('#hal_target').html(`<p>Loading publications${publications_period}...</p>`);
    $.getJSON(`${api_url}&fq=producedDateY_i:[${start_year}%20TO%20${end_year}]`, updatePublications);
}

$( document ).ready(function() {
    $('#menu_target').html(`
<div class='yearbar'>
    <button onclick='getPublications()'>All</button>
    <button onclick='getPublicationsYear(2017,2017)'>2017</button>
    <button onclick='getPublicationsYear(2016,2016)'>2016</button>
    <button onclick='getPublicationsYear(2015,2015)'>2015</button>
    <button onclick='getPublicationsYear(2001,2014)'>Before 2015</button>
</div>
<h4>Source: <a href="https://hal.archives-ouvertes.fr/">HAL</a>.</h4>
`);
    getPublicationsYear (2017, 2017);
});
